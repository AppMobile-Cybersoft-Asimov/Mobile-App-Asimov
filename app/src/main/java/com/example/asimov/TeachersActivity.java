package com.example.asimov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.Teacher;
import com.example.asimov.data.service.TeacherService;
import com.example.asimov.manager.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersActivity extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        recycler = findViewById(R.id.recyclerTeachersData);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getTeachers();
    }

    private void getTeachers() {
        TeacherService teacherService = RetrofitClient.createInstance().create(TeacherService.class);
        teacherService.getTeachersByDirector(SessionManager.getInstance().getUserId()).enqueue(new Callback<List<Teacher>>() {
            @Override
            public void onResponse(Call<List<Teacher>> call, Response<List<Teacher>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Error loading Announcement" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Teacher> lista = response.body();
                System.out.println(lista);
                TeachersAdapter adapter = new TeachersAdapter(lista);
                recycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Teacher>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
            }
        });


    }
}