package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.asimov.adapters.CourseAdapter;
import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.Courses;
import com.example.asimov.data.service.AsimovApi;
import com.example.asimov.databinding.ActivityCoursesListBinding;
import com.example.asimov.manager.SessionManager;
import com.example.asimov.utils.SpacingItemDecorator;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CoursesList extends Fragment {
    private ActivityCoursesListBinding binding;
    private List<Courses> listCourses;
    private CourseAdapter.RecyclerViewClickListener listener;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SpacingItemDecorator itemDecorator = new SpacingItemDecorator(50);

        binding = ActivityCoursesListBinding.inflate(getLayoutInflater());
        binding.recyclerCoursesList.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        binding.recyclerCoursesList.addItemDecoration(itemDecorator);
        //setOnClickListener();
        int teacherId = SessionManager.getInstance().getUserId();
        getCoursesByTeacherId(teacherId);

        return binding.getRoot();
    }

    public void setOnClickListener() {
        listener = new CourseAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getContext(), CourseInformationActivity.class);
                intent.putExtra("id", listCourses.get(position).getId());
                startActivity(intent);
            }
        };
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    private void getCoursesByTeacherId(int teacherId) {
        AsimovApi asimovApi = RetrofitClient.createInstance().create(AsimovApi.class);
        Call<List<Courses>> inter = asimovApi.getCourseByTeacherId(teacherId);

        inter.enqueue(new Callback<List<Courses>>() {
            @Override
            public void onResponse(Call<List<Courses>> call, Response<List<Courses>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Codigo de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
                listCourses = response.body();
                CourseAdapter courseAdapter = new CourseAdapter(listCourses, listener, CoursesList.this);
                binding.recyclerCoursesList.setAdapter(courseAdapter);
            }

            @Override
            public void onFailure(Call<List<Courses>> call, Throwable t) { }
        });

    }
}