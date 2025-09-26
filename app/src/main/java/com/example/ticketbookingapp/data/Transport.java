package com.example.ticketbookingapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "transport")
public class Transport {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String type;   // "Kereta" atau "Pesawat"
    public String name;   // Nama Maskapai/Kereta
    public String route;  // Rute (contoh: Jakarta - Surabaya)
    public String schedule; // Jadwal (contoh: 10:00 - 12:00)
    public double price;  // Harga tiket
    public String airline;

    public Transport() {

    }


    public Transport(String type, String name, String route, String schedule, double price) {
        this.type = type;
        this.name = name;
        this.route = route;
        this.schedule = schedule;
        this.price = price;
    }
}
