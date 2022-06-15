package com.example.asimov.data.service;

import com.example.asimov.data.model.Announcement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnnouncementsService {

    @GET("api/v1/announcements")
    Call<List<Announcement>> getAnnouncements();

}
