package com.example.databarang;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewBarangActivity extends AppCompatActivity {

    ListView listViewBarang;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_barang);

        listViewBarang = findViewById(R.id.listViewBarang);
        db = new DatabaseHelper(this);

        displayBarang();
    }

    private void displayBarang() {
        Cursor cursor = db.getAllBarang();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No items found", Toast.LENGTH_SHORT).show();
            return;
        }

        ArrayList<String> barangList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String barangData = "Kode Barang: " + cursor.getString(0) + "\n" +
                    "Nama Barang: " + cursor.getString(1) + "\n" +
                    "Merk: " + cursor.getString(2) + "\n" +
                    "Harga: " + cursor.getDouble(3) + "\n" +
                    "Jumlah Stok: " + cursor.getInt(4);
            barangList.add(barangData);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, barangList);
        listViewBarang.setAdapter(adapter);
    }
}