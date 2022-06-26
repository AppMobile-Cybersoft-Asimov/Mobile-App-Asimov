package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.Announcement;
import com.example.asimov.data.model.Competence;
import com.example.asimov.data.model.CourseItem;
import com.example.asimov.data.model.Courses;
import com.example.asimov.data.service.AnnouncementsService;
import com.example.asimov.data.service.AsimovApi;
import com.example.asimov.data.service.CourseService;
import com.example.asimov.databinding.ActivityCourseInformationBinding;

import java.util.ArrayList;
import java.util.List;

import Adapters.CourseCompetencesAdapter;
import Adapters.CourseItemsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseInformation extends Fragment {

    //private Courses course = new Courses();
    private List<Competence> competencesList;
    private List<CourseItem> courseItemsList;
    private ActivityCourseInformationBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseInformationBinding.inflate(getLayoutInflater());

        getCourseById(1);

        getCompetences();
        getCourseItems(1);

        return binding.getRoot();
    }
    private void getCourseById(int courseId){
        CourseService courseService = RetrofitClient.createInstanceWithoutToken().create(CourseService.class);
        Call<Courses> inter = courseService.getCourseById(courseId);

        inter.enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {

                binding.lblCourseName.setText("Codigo de error: ");
                Log.d("", response.body()+"");
                Courses course = response.body();
                binding.lblCourseName.setText(course.getName());
                binding.txtCourseDescription.setText(course.getDescription());
            }
            @Override
            public void onFailure(Call<Courses> call, Throwable t) { }
        });
    }
    private void getCompetences(){
        AsimovApi asimovApi = RetrofitClient.createInstanceWithoutToken().create(AsimovApi.class);
        asimovApi.getCompetences().enqueue(new Callback<List<Competence>>() {
            @Override
            public void onResponse(Call<List<Competence>> call, Response<List<Competence>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error loading Competences" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Competence> competences = response.body();
                System.out.println(competences);

                CourseCompetencesAdapter adapter = new CourseCompetencesAdapter(competences);
                binding.rvCourseCompetences.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Competence>> call, Throwable t) {
                Toast.makeText(getContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCourseItems(int courseId){
        CourseService courseService = RetrofitClient.createInstance().create(CourseService.class);
        courseService.getCourseItems(courseId).enqueue(new Callback<List<CourseItem>>() {
            @Override
            public void onResponse(Call<List<CourseItem>> call, Response<List<CourseItem>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error loading Competences" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<CourseItem> courseItemsList = response.body();

                CourseItemsAdapter adapter = new CourseItemsAdapter(courseItemsList);
                binding.rvCourseCompetences.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CourseItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}