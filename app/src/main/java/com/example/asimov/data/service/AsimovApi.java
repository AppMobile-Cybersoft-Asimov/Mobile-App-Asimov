package com.example.asimov.data.service;

import java.util.List;

import com.example.asimov.data.model.Courses;
import com.example.asimov.data.model.Teachers;
import com.example.asimov.data.model.Competence;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AsimovApi {

    @GET("api/v1/teachers/{id}")
    Call<Teachers> getTeacherById(@Path("id") int id);

    @GET("courses")
    Call<List<Courses>> getCourses();

    @GET("/api/v1/competences")
    Call<List<Competence>> getCompetences();

    @GET("/api/v1/courses/{id}")
    Call<Courses> getCourseById(@Path("id") int id);
}
