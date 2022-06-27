package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.service.CompetencesService;
import com.example.asimov.databinding.ActivityCompetencesBinding;

import java.util.List;

import com.example.asimov.adapters.ListCompetencesAdapter;
import com.example.asimov.data.model.Competence;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompetencesActivity extends Fragment {

    private ActivityCompetencesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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

    private void retrieveCompetences() {

        CompetencesService competencesService = RetrofitClient.createInstance().create(CompetencesService.class);
        Call<List<Competence>> inter = competencesService.getCompetences();

        inter.enqueue(new Callback<List<Competence>>() {
            @Override
            public void onResponse(Call<List<Competence>> call, Response<List<Competence>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Codigo de error: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Competence> listCompetences = response.body();
                ListCompetencesAdapter listCompetencesAdapter = new ListCompetencesAdapter(listCompetences);
                binding.rvCompetences.setAdapter(listCompetencesAdapter);
            }

            @Override
            public void onFailure(Call<List<Competence>> call, Throwable t) {

            }
        });
    }
}
