package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.Competence;
import com.example.asimov.data.model.Courses;
import com.example.asimov.data.service.AsimovApi;
import com.example.asimov.databinding.ActivityCourseInformationBinding;

import java.util.ArrayList;
import java.util.List;

import Adapters.CourseCompetencesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseInformation extends Fragment {

    //private Courses course = new Courses();
    private List<Competence> competencesList;
    private ActivityCourseInformationBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseInformationBinding.inflate(getLayoutInflater());

        getCourseById(0);
        getCompetences();

        return binding.getRoot();
    }
    private void getCourseById(int courseId){
        AsimovApi asimovApi = RetrofitClient.createInstance().create(AsimovApi.class);
        Call<Courses> inter = asimovApi.getCourseById(courseId);

        inter.enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {
                if(!response.isSuccessful()) {
                    binding.lblCourseName.setText("Codigo de error: "+response.code());
                    return;
                }
                Courses course = response.body();
                System.out.print(course.getName());
                binding.lblCourseName.setText(course.getName());
                binding.txtCourseDescription.setText(course.getDescription());

            }

            @Override
            public void onFailure(Call<Courses> call, Throwable t) { }
        });
    }
    private void getCompetences(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://asimov.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AsimovApi asimovApi = retrofit.create(AsimovApi.class);
        Call<List<Competence>> inter = asimovApi.getCompetences();

        inter.enqueue(new Callback<List<Competence>>() {
            @Override
            public void onResponse(Call<List<Competence>> call, Response<List<Competence>> response) {
                List<Competence> list = response.body();
                competencesList = new ArrayList<Competence>();
                //for(Competence co:list){
                //    competencesList.add(new Competence(co.getId(), co.getTitle(),
                //            co.getDescription()));
                //}
                //CourseCompetencesAdapter adapter = new CourseCompetencesAdapter(competencesList);
                //binding.rvCourseCompetences.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Competence>> call, Throwable t) { }
        });
    }
}