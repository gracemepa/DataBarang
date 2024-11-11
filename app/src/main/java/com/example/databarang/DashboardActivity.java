package com.example.databarang;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    Button btnAddBarang, btnViewBarang, btnUpdateBarang, btnDeleteBarang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnAddBarang = findViewById(R.id.btnAddBarang);
        btnViewBarang = findViewById(R.id.btnViewBarang);
        btnUpdateBarang = findViewById(R.id.btnUpdateBarang);
        btnDeleteBarang = findViewById(R.id.btnDeleteBarang);

        btnAddBarang.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, AddBarangActivity.class)));
        btnViewBarang.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, ViewBarangActivity.class)));
        btnUpdateBarang.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, EditBarangActivity.class)));
        btnDeleteBarang.setOnClickListener(v -> startActivity(new Intent(DashboardActivity.this, DeleteBarangActivity.class)));
    }
}