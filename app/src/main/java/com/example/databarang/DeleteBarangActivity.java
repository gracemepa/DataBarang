package com.example.databarang;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteBarangActivity extends AppCompatActivity {

    EditText edtKodeBarang;
    Button btnDeleteBarang;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_barang);

        db = new DatabaseHelper(this);

        edtKodeBarang = findViewById(R.id.edtKodeBarang);
        btnDeleteBarang = findViewById(R.id.btnDeleteBarang);

        btnDeleteBarang.setOnClickListener(v -> {
            String kodeBarang = edtKodeBarang.getText().toString();

            if (kodeBarang.isEmpty()) {
                Toast.makeText(DeleteBarangActivity.this, "Kode Barang is required", Toast.LENGTH_SHORT).show();
            } else {
                boolean isDeleted = db.deleteBarang(kodeBarang);
                if (isDeleted) {
                    Toast.makeText(DeleteBarangActivity.this, "Barang Deleted", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DeleteBarangActivity.this, "Failed to Delete Barang", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}