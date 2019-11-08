package com.company.p9.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.company.p9.api.ApiModule;
import com.company.p9.db.ItemDao;
import com.company.p9.db.ItemDatabase;
import com.company.p9.model.ApiResponse;
import com.company.p9.model.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private ItemDao itemDao;

    public MutableLiveData<String> termLiveData = new MutableLiveData<>();

    public LiveData<List<Item>> apiItemList = Transformations.switchMap(termLiveData, new Function<String, LiveData<List<Item>>>() {
        @Override
        public LiveData<List<Item>> apply(final String input) {
            final MutableLiveData<List<Item>> items = new MutableLiveData<>();

            ApiModule.api.buscar().enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    List<Item> itemList = new ArrayList<>();
                    for(Item item:response.body().items){
                        if(item.name.contains(input)){
                            itemList.add(item);
                        }
                    }
                    items.setValue(itemList);
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Log.e("ABC", "API NETWORK FAILURE");
                }
            });

            return items;
        }
    });

    public LiveData<List<Item>> dbItemList = Transformations.switchMap(termLiveData, new Function<String, LiveData<List<Item>>>() {
        @Override
        public LiveData<List<Item>> apply(String input) {
            return itemDao.getItems("%" + input + "%");
        }
    });

    public MainViewModel(@NonNull Application application) {
        super(application);

        itemDao = ItemDatabase.getInstance(application).itemDao();

        termLiveData.setValue("");
    }

    public void setSearchTerm(String newTerm){
        termLiveData.setValue(newTerm);
    }
}
