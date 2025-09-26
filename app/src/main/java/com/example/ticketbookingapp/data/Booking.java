package com.example.ticketbookingapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookings")
public class Booking {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long userId;
    public long transportId;
    public String passengerName;
    public String date;
    public String bookingCode;
    public String status; // NEW: "UNPAID" atau "PAID"

    public Booking(long userId, long transportId, String passengerName, String date, String bookingCode, String status) {
        this.userId = userId;
        this.transportId = transportId;
        this.passengerName = passengerName;
        this.date = date;
        this.bookingCode = bookingCode;
        this.status = status;
    }
}
