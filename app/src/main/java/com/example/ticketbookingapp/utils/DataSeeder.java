package com.example.ticketbookingapp.utils;

import android.content.Context;

import com.example.ticketbookingapp.data.AppDatabase;
import com.example.ticketbookingapp.data.Transport;

public class DataSeeder {

    public static void seed(Context context) {
        AppDatabase db = AppDatabase.getInstance(context);

        // Kalau sudah ada data, jangan insert lagi
        if (!db.transportDao().getAll().isEmpty()) {
            return;
        }

        // 1
        Transport t1 = new Transport();
        t1.type = "Pesawat";
        t1.airline = "Garuda Indonesia";
        t1.route = "Jakarta - Bali";
        t1.schedule = "2025-10-01 08:00";
        t1.price = 1500000;

        // 2
        Transport t2 = new Transport();
        t2.type = "Pesawat";
        t2.airline = "Lion Air";
        t2.route = "Jakarta - Surabaya";
        t2.schedule = "2025-10-01 09:30";
        t2.price = 900000;

        // 3
        Transport t3 = new Transport();
        t3.type = "Pesawat";
        t3.airline = "AirAsia";
        t3.route = "Jakarta - Singapore";
        t3.schedule = "2025-10-02 07:00";
        t3.price = 1200000;

        // 4
        Transport t4 = new Transport();
        t4.type = "Pesawat";
        t4.airline = "Citilink";
        t4.route = "Surabaya - Bali";
        t4.schedule = "2025-10-02 12:00";
        t4.price = 700000;

        // 5
        Transport t5 = new Transport();
        t5.type = "Pesawat";
        t5.airline = "Batik Air";
        t5.route = "Medan - Jakarta";
        t5.schedule = "2025-10-03 10:00";
        t5.price = 1100000;

        // 6
        Transport t6 = new Transport();
        t6.type = "Kereta";
        t6.airline = "KAI Argo Parahyangan";
        t6.route = "Bandung - Jakarta";
        t6.schedule = "2025-10-01 06:00";
        t6.price = 150000;

        // 7
        Transport t7 = new Transport();
        t7.type = "Kereta";
        t7.airline = "KAI Taksaka";
        t7.route = "Yogyakarta - Jakarta";
        t7.schedule = "2025-10-02 08:30";
        t7.price = 350000;

        // 8
        Transport t8 = new Transport();
        t8.type = "Kereta";
        t8.airline = "KAI Gajayana";
        t8.route = "Malang - Jakarta";
        t8.schedule = "2025-10-02 16:00";
        t8.price = 500000;

        // 9
        Transport t9 = new Transport();
        t9.type = "Kereta";
        t9.airline = "KAI Matarmaja";
        t9.route = "Malang - Bandung";
        t9.schedule = "2025-10-03 20:00";
        t9.price = 200000;

        // 10
        Transport t10 = new Transport();
        t10.type = "Kereta";
        t10.airline = "KAI Turangga";
        t10.route = "Bandung - Surabaya";
        t10.schedule = "2025-10-04 05:30";
        t10.price = 400000;

        // Insert semua ke DB
        db.transportDao().insert(t1);
        db.transportDao().insert(t2);
        db.transportDao().insert(t3);
        db.transportDao().insert(t4);
        db.transportDao().insert(t5);
        db.transportDao().insert(t6);
        db.transportDao().insert(t7);
        db.transportDao().insert(t8);
        db.transportDao().insert(t9);
        db.transportDao().insert(t10);
    }
}
