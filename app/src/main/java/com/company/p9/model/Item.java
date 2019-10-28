package com.company.p9.model;

import androidx.room.Entity;

@Entity
public class Item {
    public String name;
    public String date;

    public Item(){}

    public Item(String name, String date) {
        this.name = name;
        this.date = date;
    }
}
