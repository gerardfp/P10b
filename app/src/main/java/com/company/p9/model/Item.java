package com.company.p9.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Item {
    @PrimaryKey (autoGenerate = true)
    public int id;

    public String name;
    public String date;

    public Item(){}

    public Item(String name, String date) {
        this.name = name;
        this.date = date;
    }
}
