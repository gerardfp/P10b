package com.company.p9.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiModule {
    public static Api api = new Retrofit.Builder()
            .baseUrl("https://github.com/gerardfp/p9/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api.class);
}
