package com.example.ticketbookingapp.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketbookingapp.R;
import com.example.ticketbookingapp.data.AppDatabase;
import com.example.ticketbookingapp.data.Transport;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TransportListActivity extends AppCompatActivity implements TransportAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private TransportAdapter adapter;
    private AppDatabase db;
    private FloatingActionButton fabAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_list);

        db = AppDatabase.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, TransportFormActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        List<Transport> list = db.transportDao().getAll();
        adapter = new TransportAdapter(list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onEdit(Transport transport) {
        Intent intent = new Intent(this, TransportFormActivity.class);
        intent.putExtra("transportId", transport.id);
        startActivity(intent);
    }

    @Override
    public void onDelete(Transport transport) {
        db.transportDao().delete(transport);
        loadData();
    }
}
