package com.example.asimov.data;

import com.example.asimov.manager.SessionManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit createInstance() {

        String token = SessionManager.getInstance().getAuthToken();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (token != null) {
            httpClient.addInterceptor(chain -> {
                Request request = chain.request().newBuilder().addHeader("Authorization", token).build();
                return chain.proceed(request);
            });
        }

        return new Retrofit.Builder()
                .baseUrl("https://asimov.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
    }

    public static Retrofit createInstanceWithoutToken() {
        return new Retrofit.Builder()
                .baseUrl("https://asimov.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
