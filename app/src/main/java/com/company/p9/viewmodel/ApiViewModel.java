package com.company.p9.viewmodel;

import android.app.Application;

import com.company.p9.api.ApiModule;
import com.company.p9.model.ApiResponse;

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

    public LiveData<ApiResponse> getItems(String term){
        final MutableLiveData<ApiResponse> itunesResponse = new MutableLiveData<>();

        ApiModule.api.buscar().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                itunesResponse.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {}
        });

        return itunesResponse;
    }
}
