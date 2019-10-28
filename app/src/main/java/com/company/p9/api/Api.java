package com.company.p9.api;

import com.company.p9.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("/api.json")
    Call<ApiResponse> buscar();
}
