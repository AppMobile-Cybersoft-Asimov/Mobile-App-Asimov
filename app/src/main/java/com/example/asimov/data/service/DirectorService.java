package com.example.asimov.data.service;

import com.example.asimov.data.model.Announcement;
import com.example.asimov.data.model.LoginRequest;
import com.example.asimov.data.model.LoginResponse;
import com.example.asimov.data.model.RegisterRequest;
import com.example.asimov.data.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DirectorService {

    @POST("auth/sign-in/director")
    Call<LoginResponse> login(@Body LoginRequest login);
    @POST("auth/sign-up/director")
    Call<RegisterResponse> register(@Body RegisterRequest request);
    @POST("api/v1/announcements")
    Call<Announcement> publishAnnouncement(@Body Announcement announcement);
}
