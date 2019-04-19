package com.naeem.assignment.Classes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.naeem.assignment.Classes.Constants.BASE_URL;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {
        if (retrofit == null)
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        return retrofit;
    }
}
