package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asimov.databinding.ActivityAnnouncementsBinding;
import com.example.asimov.databinding.ActivityCompetencesBinding;
import com.example.asimov.databinding.ActivityDashboardDirectorBinding;

import java.util.ArrayList;
import java.util.List;

import Adapters.ListCompetencesAdapter;
import Interfaces.AsimovApi;
import Models.Competence;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CompetencesActivity extends Fragment {

    private ActivityCompetencesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityCompetencesBinding.inflate(getLayoutInflater());
        binding.rvCompetences.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false));

        retrieveCompetences();

        return binding.getRoot();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void retrieveCompetences(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://asimov.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AsimovApi asimovApi = retrofit.create(AsimovApi.class);
        Call<List<Competence>> inter = asimovApi.getCompetences();

        inter.enqueue(new Callback<List<Competence>>() {
            @Override
            public void onResponse(Call<List<Competence>> call, Response<List<Competence>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), "Codigo de error: "+ response.code(), Toast.LENGTH_SHORT).show();
                    List<Competence> listCompetences = new ArrayList<>();

                    for(int i = 0; i <= 5; i++){
                        Competence competence = new Competence(i,"Competencia " + Integer.toString(i+1), "descripcion");
                        listCompetences.add(competence);
                    }
                    ListCompetencesAdapter listCompetencesAdapter = new ListCompetencesAdapter(listCompetences);
                    binding.rvCompetences.setAdapter(listCompetencesAdapter);
                }
                else {
                    List<Competence> listCompetences = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<Competence>> call, Throwable t) {

            }
        });
    }
}