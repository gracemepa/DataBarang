package com.example.databarang;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditBarangActivity extends AppCompatActivity {

    EditText edtKodeBarang, edtNamaBarang, edtMerk, edtHargaBarang, edtJumlahStok;
    Button btnUpdateBarang;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        db = new DatabaseHelper(this);

        edtKodeBarang = findViewById(R.id.edtKodeBarang);
        edtNamaBarang = findViewById(R.id.edtNamaBarang);
        edtMerk = findViewById(R.id.edtMerk);
        edtHargaBarang = findViewById(R.id.edtHargaBarang);
        edtJumlahStok = findViewById(R.id.edtJumlahStok);
        btnUpdateBarang = findViewById(R.id.btnUpdateBarang);

        btnUpdateBarang.setOnClickListener(v -> {
            String kodeBarang = edtKodeBarang.getText().toString();
            String namaBarang = edtNamaBarang.getText().toString();
            String merk = edtMerk.getText().toString();
            double hargaBarang = Double.parseDouble(edtHargaBarang.getText().toString());
            int jumlahStok = Integer.parseInt(edtJumlahStok.getText().toString());

            if (kodeBarang.isEmpty() || namaBarang.isEmpty() || merk.isEmpty()) {
                Toast.makeText(EditBarangActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                boolean isUpdated = db.updateBarang(kodeBarang, namaBarang, merk, hargaBarang, jumlahStok);
                if (isUpdated) {
                    Toast.makeText(EditBarangActivity.this, "Barang Updated", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(EditBarangActivity.this, "Failed to Update Barang", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}