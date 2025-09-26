package com.example.ticketbookingapp.ui;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbookingapp.R;
import com.example.ticketbookingapp.data.AppDatabase;
import com.example.ticketbookingapp.data.Booking;
import com.example.ticketbookingapp.data.Transport;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class ETicketActivity extends AppCompatActivity {
    private TextView tvInfo;
    private ImageView ivQr;
    private AppDatabase db;

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eticket);

        tvInfo = findViewById(R.id.tvInfo);
        ivQr = findViewById(R.id.ivQr);
        db = AppDatabase.getInstance(this);

        long bookingId = getIntent().getLongExtra("bookingId", -1);
        Booking booking = db.bookingDao().findById(bookingId);
        Transport t = db.transportDao().getAll().stream().filter(x -> x.id == booking.transportId).findFirst().orElse(null);

        if(booking != null && t != null) {
            String info = "E-Tiket\n" +
                    "Kode: " + booking.bookingCode + "\n" +
                    "Nama: " + booking.passengerName + "\n" +
                    "Tanggal: " + booking.date + "\n" +
                    "Transport: " + t.name + " (" + t.type + ")\n" +
                    "Rute: " + t.route + "\n" +
                    "Jadwal: " + t.schedule + "\n" +
                    "Harga: Rp " + t.price + "\n" +
                    "Status: " + booking.status;

            tvInfo.setText(info);

            try {
                BarcodeEncoder encoder = new BarcodeEncoder();
                Bitmap bitmap = encoder.encodeBitmap(booking.bookingCode, BarcodeFormat.QR_CODE, 400, 400);
                ivQr.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}
