package com.example.ticketbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbookingapp.ui.SearchActivity;
import com.example.ticketbookingapp.ui.TransportListActivity;
import com.example.ticketbookingapp.ui.LoginActivity;
import com.example.ticketbookingapp.utils.SessionManager;
import com.example.ticketbookingapp.utils.DataSeeder;

public class MainActivity extends AppCompatActivity {
    private Button btnSearch, btnLogout, btnManageTransport;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);

        // 🚀 Seed data dummy transportasi (jalan hanya kalau DB kosong)
        DataSeeder.seed(this);

        // Ambil tombol dari layout
        btnSearch = findViewById(R.id.btnSearch);
        btnLogout = findViewById(R.id.btnLogout);
        btnManageTransport = findViewById(R.id.btnManageTransport);

        // Tombol cari tiket
        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        });

        // Tombol kelola transportasi (CRUD)
        btnManageTransport.setOnClickListener(v -> {
            Intent intent = new Intent(this, TransportListActivity.class);
            startActivity(intent);
        });

        // Tombol logout
        btnLogout.setOnClickListener(v -> {
            sessionManager.logout();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}
