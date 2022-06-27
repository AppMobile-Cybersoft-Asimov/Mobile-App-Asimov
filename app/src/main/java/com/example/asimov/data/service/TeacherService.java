package com.example.asimov.data.service;

import com.example.asimov.data.model.LoginRequest;
import com.example.asimov.data.model.LoginResponse;
import com.example.asimov.data.model.RegisterRequest;
import com.example.asimov.data.model.RegisterResponse;
import com.example.asimov.data.model.Teacher;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TeacherService {

    @POST("auth/sign-in/teacher")
    Call<LoginResponse> login(@Body LoginRequest login);
    @POST("auth/sign-up/teacher")
    Call<RegisterResponse> register(@Body RegisterRequest login);
    @GET("/api/v1/directors/{directorId}/teachers")
    Call<List<Teacher>> getTeachersByDirector(@Path("directorId") Integer id);
}
