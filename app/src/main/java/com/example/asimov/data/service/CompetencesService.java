package com.example.asimov.data.service;

import com.example.asimov.data.model.Competence;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CompetencesService {
    @GET("/api/v1/competences")
    Call<List<Competence>> getCompetences();
}
