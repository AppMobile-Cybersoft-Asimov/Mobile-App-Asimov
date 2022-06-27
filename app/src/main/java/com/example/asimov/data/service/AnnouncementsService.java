package com.example.asimov.data.service;

import com.example.asimov.data.model.Announcement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AnnouncementsService {

    @GET("api/v1/announcements")
    Call<List<Announcement>> getAnnouncements();

    @DELETE("api/v1/announcements/{id}")
    Call<Announcement> deleteAnnouncement(@Path("id") Long id);
}
