package com.example.ticketbookingapp.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookingDao {
    @Insert
    long insert(Booking booking);

    @Query("SELECT * FROM bookings WHERE userId = :userId")
    List<Booking> getBookingsByUser(long userId);

    @Query("SELECT * FROM bookings WHERE id = :id LIMIT 1")
    Booking findById(long id);

    @Query("UPDATE bookings SET status = :status WHERE id = :id")
    void updateStatus(long id, String status);
}
