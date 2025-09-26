package com.example.ticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketbookingapp.R;
import com.example.ticketbookingapp.data.AppDatabase;
import com.example.ticketbookingapp.data.Transport;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchAdapter.OnBuyClickListener {
    private EditText etKeyword;
    private Button btnSearch;
    private RecyclerView recyclerView;
    private AppDatabase db;
    private SearchAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etKeyword = findViewById(R.id.etKeyword);
        btnSearch = findViewById(R.id.btnSearch);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = AppDatabase.getInstance(this);

        btnSearch.setOnClickListener(v -> {
            String key = etKeyword.getText().toString().trim();
            List<Transport> list = db.transportDao().getAll();

            List<Transport> filtered = new ArrayList<>();
            if (!key.isEmpty()) {
                for (Transport t : list) {
                    if (t.route.toLowerCase().contains(key.toLowerCase())) {
                        filtered.add(t);
                    }
                }
            } else {
                filtered.addAll(list);
            }

            adapter = new SearchAdapter(filtered, this);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onBuy(Transport transport) {
        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra("transportId", transport.id);
        startActivity(intent);
    }
}
