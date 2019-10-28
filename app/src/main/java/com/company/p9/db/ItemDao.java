package com.company.p9.db;

import com.company.p9.model.Item;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public abstract class ItemDao {
    @Insert
    public abstract void insertItem(Item item);

    @Query("SELECT * FROM Item WHERE name LIKE :term")
    public abstract LiveData<List<Item>> getItems(String term);
}
