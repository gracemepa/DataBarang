package com.example.databarang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "InventoryDB.db";
    private static final int DATABASE_VERSION = 1;

    // Nama tabel dan kolom untuk pengguna
    private static final String USER_TABLE = "users";
    private static final String COLUMN_USER_ID = "ID";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";

    // Nama tabel dan kolom untuk data barang
    private static final String BARANG_TABLE = "barang";
    private static final String COLUMN_KODE_BARANG = "kode_barang";
    private static final String COLUMN_NAMA_BARANG = "nama_barang";
    private static final String COLUMN_MERK = "merk";
    private static final String COLUMN_HARGA_BARANG = "harga_barang";
    private static final String COLUMN_JUMLAH_STOK = "jumlah_stok";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Membuat tabel users untuk login dan registrasi
        String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(CREATE_USER_TABLE);

        // Membuat tabel barang untuk menyimpan data barang
        String CREATE_BARANG_TABLE = "CREATE TABLE " + BARANG_TABLE + " (" +
                COLUMN_KODE_BARANG + " TEXT PRIMARY KEY, " +
                COLUMN_NAMA_BARANG + " TEXT, " +
                COLUMN_MERK + " TEXT, " +
                COLUMN_HARGA_BARANG + " REAL, " +
                COLUMN_JUMLAH_STOK + " INTEGER)";
        db.execSQL(CREATE_BARANG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Menghapus tabel lama jika ada versi baru
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BARANG_TABLE);
        onCreate(db);
    }

    // Menyisipkan data pengguna baru ke tabel users
    public boolean insertUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = db.insert(USER_TABLE, null, contentValues);
        return result != -1; // Mengembalikan true jika penyisipan berhasil
    }

    // Memeriksa apakah pengguna dengan username dan password tertentu ada di database
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?", new String[]{username, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists; // Mengembalikan true jika pengguna ditemukan
    }

    // Menyisipkan data barang baru ke tabel barang
    public boolean insertBarang(String kodeBarang, String namaBarang, String merk, double hargaBarang, int jumlahStok) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_KODE_BARANG, kodeBarang);
        contentValues.put(COLUMN_NAMA_BARANG, namaBarang);
        contentValues.put(COLUMN_MERK, merk);
        contentValues.put(COLUMN_HARGA_BARANG, hargaBarang);
        contentValues.put(COLUMN_JUMLAH_STOK, jumlahStok);
        long result = db.insert(BARANG_TABLE, null, contentValues);
        return result != -1; // Mengembalikan true jika penyisipan berhasil
    }

    // Mengambil semua data barang
    public Cursor getAllBarang() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + BARANG_TABLE, null); // Mengembalikan Cursor untuk membaca data barang
    }

    // Memperbarui data barang berdasarkan kode_barang
    public boolean updateBarang(String kodeBarang, String namaBarang, String merk, double hargaBarang, int jumlahStok) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMA_BARANG, namaBarang);
        contentValues.put(COLUMN_MERK, merk);
        contentValues.put(COLUMN_HARGA_BARANG, hargaBarang);
        contentValues.put(COLUMN_JUMLAH_STOK, jumlahStok);
        int result = db.update(BARANG_TABLE, contentValues, COLUMN_KODE_BARANG + "=?", new String[]{kodeBarang});
        return result > 0; // Mengembalikan true jika ada baris yang diperbarui
    }

    // Menghapus data barang berdasarkan kode_barang
    public boolean deleteBarang(String kodeBarang) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(BARANG_TABLE, COLUMN_KODE_BARANG + "=?", new String[]{kodeBarang});
        return result > 0; // Mengembalikan true jika ada baris yang dihapus
    }
}