package com.example.ticketbookingapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticketbookingapp.R;
import com.example.ticketbookingapp.data.AppDatabase;
import com.example.ticketbookingapp.data.Transport;

public class TransportFormActivity extends AppCompatActivity {
    private EditText etType, etName, etRoute, etSchedule, etPrice;
    private Button btnSave;
    private AppDatabase db;
    private long transportId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_form);

        etType = findViewById(R.id.etType);
        etName = findViewById(R.id.etName);
        etRoute = findViewById(R.id.etRoute);
        etSchedule = findViewById(R.id.etSchedule);
        etPrice = findViewById(R.id.etPrice);
        btnSave = findViewById(R.id.btnSave);

        db = AppDatabase.getInstance(this);

        transportId = getIntent().getLongExtra("id", -1);
        if(transportId != -1){
            for(Transport t : db.transportDao().getAll()){
                if(t.id == transportId){
                    etType.setText(t.type);
                    etName.setText(t.name);
                    etRoute.setText(t.route);
                    etSchedule.setText(t.schedule);
                    etPrice.setText(String.valueOf(t.price));
                    break;
                }
            }
        }

        btnSave.setOnClickListener(v -> {
            String type = etType.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String route = etRoute.getText().toString().trim();
            String schedule = etSchedule.getText().toString().trim();
            String priceStr = etPrice.getText().toString().trim();

            if(type.isEmpty() || name.isEmpty() || route.isEmpty() || schedule.isEmpty() || priceStr.isEmpty()){
                Toast.makeText(this, "Isi semua data", Toast.LENGTH_SHORT).show();
                return;
            }

            double price = Double.parseDouble(priceStr);

            if(transportId == -1){
                Transport newT = new Transport(type, name, route, schedule, price);
                db.transportDao().insert(newT);
                Toast.makeText(this, "Data ditambahkan", Toast.LENGTH_SHORT).show();
            } else {
                Transport t = new Transport(type, name, route, schedule, price);
                t.id = transportId;
                db.transportDao().update(t);
                Toast.makeText(this, "Data diperbarui", Toast.LENGTH_SHORT).show();
            }

            finish();
        });
    }
}
