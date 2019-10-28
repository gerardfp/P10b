package com.company.p9.viewmodel;

import android.app.Application;
import android.util.Log;

import com.company.p9.api.ApiModule;
import com.company.p9.model.ApiResponse;
import com.company.p9.model.Item;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiViewModel extends AndroidViewModel {
    public ApiViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Item>> getItems(final String term){
        final MutableLiveData<List<Item>> items = new MutableLiveData<>();

        ApiModule.api.buscar().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<Item> itemList = new ArrayList<>();
                for(Item item:response.body().items){
                    if(item.name.contains(term)){
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
}
