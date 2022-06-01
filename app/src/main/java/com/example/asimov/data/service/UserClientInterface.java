package com.example.asimov.data.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserClientInterface {
    @POST("login")
    Call<User> login(@Body login login);

    @GET("secretinfo")
    Call<ResponseBody> getSecret(@Header("Authorization") String AuthToken);
}
