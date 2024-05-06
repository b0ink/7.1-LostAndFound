package com.example.lostandfound;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface LostItemDao {
    @Query("SELECT * FROM lost_items")
    List<LostItem> getAllLostItems();

    @Query("SELECT * FROM lost_items WHERE id = :itemId")
    LostItem getLostItemById(int itemId);

    @Insert
    void insert(LostItem item);

    @Delete
    void delete(LostItem item);
}
