package com.example.ticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbookingapp.R;
import com.example.ticketbookingapp.data.AppDatabase;
import com.example.ticketbookingapp.data.Booking;

public class PaymentActivity extends AppCompatActivity {
    private Spinner spinnerMethod;
    private Button btnPay;
    private AppDatabase db;
    private long bookingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        spinnerMethod = findViewById(R.id.spinnerMethod);
        btnPay = findViewById(R.id.btnPay);
        db = AppDatabase.getInstance(this);

        bookingId = getIntent().getLongExtra("bookingId", -1);

        // opsi metode pembayaran
        String[] methods = {"Transfer Bank", "E-Wallet", "Kartu Kredit"};
        spinnerMethod.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, methods));

        btnPay.setOnClickListener(v -> {
            Booking booking = db.bookingDao().findById(bookingId);
            if(booking != null) {
                db.bookingDao().updateStatus(bookingId, "PAID");
                Toast.makeText(this, "Pembayaran berhasil via " + spinnerMethod.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();

                // buka E-Ticket
                Intent intent = new Intent(this, ETicketActivity.class);
                intent.putExtra("bookingId", bookingId);
                startActivity(intent);
                finish();
            }
        });
    }
}
