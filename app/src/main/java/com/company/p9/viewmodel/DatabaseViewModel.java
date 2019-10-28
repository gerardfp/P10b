package com.company.p9.viewmodel;

import android.app.Application;

import com.company.p9.db.ItemDao;
import com.company.p9.db.ItemDatabase;
import com.company.p9.model.Item;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class DatabaseViewModel extends AndroidViewModel {
    ItemDao itemDao;

    public DatabaseViewModel(@NonNull Application application) {
        super(application);
        itemDao = ItemDatabase.getInstance(application).itemDao();
    }

    public LiveData<List<Item>> getItems(String term){
        return itemDao.getItems("%"+term+"%");
    }
}
