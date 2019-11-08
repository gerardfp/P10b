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
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract AppDao itemDao();

    public static AppDatabase getInstance(final Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "item-db")
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

    static void insertarDatosIniciales(final AppDao tareasDao){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 200; i++) {
                    tareasDao.insertItem(new Item("Item"+i, (int) (Math.random()*31)+"/"+(int) (Math.random()*12)+"/"+(int) (Math.random()*25+2000)));
                }
            }
        });
    }
}
