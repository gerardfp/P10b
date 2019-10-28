package com.company.p9.api;

import com.company.p9.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("search/")
    Call<ApiResponse> buscar(@Query("term") String term);
}
