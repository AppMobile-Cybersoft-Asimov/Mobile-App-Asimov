package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.CourseCompetence;
import com.example.asimov.data.model.CourseItem;
import com.example.asimov.data.model.Courses;
import com.example.asimov.data.service.CourseService;
import com.example.asimov.databinding.ActivityCourseInformationBinding;

import java.util.List;

import com.example.asimov.adapters.CourseCompetencesAdapter;
import com.example.asimov.adapters.CourseItemsAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseInformationActivity extends Fragment {

    //private Courses course = new Courses();
    private List<CourseCompetence> competencesList;
    private List<CourseItem> courseItemsList;
    private ActivityCourseInformationBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCourseInformationBinding.inflate(getLayoutInflater());
        binding.rvCourseCompetences.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.rvCourseItems.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        String stringId = getArguments().getString("id");
        int courseId = Integer.parseInt(stringId);

        //Bundle extra = getActivity().getIntent().getBundleExtra("id");

        /*
        if (extra != null) {
            courseId = Integer.parseInt(extra.getString("id"));
        }
        */

        getCourseById(courseId);
        getCourseCompetences(courseId);
        getCourseItems(courseId);

        return binding.getRoot();
    }

    private void getCourseById(int courseId) {
        CourseService courseService = RetrofitClient.createInstanceWithoutToken().create(CourseService.class);
        Call<Courses> inter = courseService.getCourseById(courseId);

        inter.enqueue(new Callback<Courses>() {
            @Override
            public void onResponse(Call<Courses> call, Response<Courses> response) {

                binding.lblCourseName.setText("Codigo de error: ");
                Log.d("", response.body() + "");
                Courses course = response.body();
                binding.lblCourseName.setText(course.getName());
                binding.txtCourseDescription.setText(course.getDescription());
            }

            @Override
            public void onFailure(Call<Courses> call, Throwable t) {
            }
        });
    }

    private void getCourseCompetences(int courseId) {
        CourseService courseService = RetrofitClient.createInstance().create(CourseService.class);
        courseService.getCourseCompetences(courseId).enqueue(new Callback<List<CourseCompetence>>() {
            @Override
            public void onResponse(Call<List<CourseCompetence>> call, Response<List<CourseCompetence>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error loading Competences" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<CourseCompetence> competences = response.body();
                System.out.println(competences);

                CourseCompetencesAdapter adapter = new CourseCompetencesAdapter(competences);
                binding.rvCourseCompetences.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CourseCompetence>> call, Throwable t) {
                Toast.makeText(getContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCourseItems(int courseId) {
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
                binding.rvCourseItems.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<CourseItem>> call, Throwable t) {
                Toast.makeText(getContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}