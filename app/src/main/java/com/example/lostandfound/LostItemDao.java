package com.example.lostandfound;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.ArrayList;
import java.util.List;

@Dao
public interface LostItemDao {
    @Query("SELECT * FROM lost_items")
    List<LostItem> getAllLostItems();

    @Insert
    void insert(LostItem item);
}
