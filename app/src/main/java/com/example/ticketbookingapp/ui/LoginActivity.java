package com.example.ticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbookingapp.MainActivity;
import com.example.ticketbookingapp.R;
import com.example.ticketbookingapp.data.AppDatabase;
import com.example.ticketbookingapp.data.User;
import com.example.ticketbookingapp.utils.HashUtil;
import com.example.ticketbookingapp.utils.SessionManager;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString();

            if(email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Isi email & password", Toast.LENGTH_SHORT).show();
                return;
            }

            AppDatabase db = AppDatabase.getInstance(this);
            User user = db.userDao().findByEmail(email);

            if(user == null) {
                Toast.makeText(this, "User tidak ditemukan", Toast.LENGTH_SHORT).show();
                return;
            }

            String hash = HashUtil.sha256(pass);
            if(hash.equals(user.passwordHash)) {
                new SessionManager(this).saveUser(user.id);
                Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,  MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Password salah", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }
}
