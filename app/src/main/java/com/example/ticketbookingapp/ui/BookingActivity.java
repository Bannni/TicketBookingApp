package com.example.ticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbookingapp.R;
import com.example.ticketbookingapp.data.AppDatabase;
import com.example.ticketbookingapp.data.Booking;
import com.example.ticketbookingapp.utils.SessionManager;

import java.util.UUID;

public class BookingActivity extends AppCompatActivity {
    private EditText etPassenger, etDate;
    private Button btnBook;
    private AppDatabase db;
    private long transportId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        etPassenger = findViewById(R.id.etPassenger);
        etDate = findViewById(R.id.etDate);
        btnBook = findViewById(R.id.btnBook);

        db = AppDatabase.getInstance(this);
        transportId = getIntent().getLongExtra("transportId", -1);

        btnBook.setOnClickListener(v -> {
            String passenger = etPassenger.getText().toString().trim();
            String date = etDate.getText().toString().trim();

            if (passenger.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Isi semua data", Toast.LENGTH_SHORT).show();
                return;
            }

            long userId = new SessionManager(this).getUserId();
            String code = "BOOK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

            // status awal UNPAID
            Booking booking = new Booking(userId, transportId, passenger, date, code, "UNPAID");
            long id = db.bookingDao().insert(booking);

            // langsung redirect ke PaymentActivity, bukan ETicketActivity
            Intent intent = new Intent(this, PaymentActivity.class);
            intent.putExtra("bookingId", id);
            startActivity(intent);
            finish();
        });
    }
}
