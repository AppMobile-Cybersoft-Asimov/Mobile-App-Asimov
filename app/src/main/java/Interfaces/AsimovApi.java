package Interfaces;

import java.util.List;

import Models.Competence;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AsimovApi {

    @GET("/api/v1/competences")
    Call<List<Competence>> getCompetences();
}
