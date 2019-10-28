package com.company.p9.db;

import android.content.Context;
import android.os.AsyncTask;

import com.company.p9.model.Item;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database( entities = {Item.class}, version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    private static ItemDatabase INSTANCE;

    public abstract ItemDao itemDao();

    public static ItemDatabase getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, ItemDatabase.class, "item-db")
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            insertarDatosIniciales(getInstance(context).itemDao());
                        }
                    })
                    .build();
        }
        return INSTANCE;
    }

    static void insertarDatosIniciales(final ItemDao tareasDao){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    tareasDao.insertItem(new Item("Item"+i, i+"/"+i+"/"+i));
                }
            }
        });
    }
}
