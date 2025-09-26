package com.example.ticketbookingapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransportDao {
    @Insert
    long insert(Transport transport);

    @Update
    int update(Transport transport);

    @Delete
    int delete(Transport transport);

    @Query("SELECT * FROM transport ORDER BY id DESC")
    List<Transport> getAll();

    @Query("SELECT * FROM transport WHERE id = :id LIMIT 1")
    Transport findById(long id);
}
