package com.example.asimov.data.service;

import com.example.asimov.data.model.LoginRequest;
import com.example.asimov.data.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TeacherService {

    @POST("auth/sign-in/teacher")
    Call<LoginResponse> login(@Body LoginRequest login);

}
