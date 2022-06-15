package com.example.asimov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.asimov.data.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeachersActivity extends AppCompatActivity {

    RecyclerView recycler;
    List<Teacher> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

        recycler = findViewById(R.id.recyclerTeachersData);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        getTeachers();
    }

    private void getTeachers() {
        List<Teacher> teacher = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            teacher.add(new Teacher((long) i, "Email " + i, "Name " + i, "Phone " + i));
        }
        TeachersAdapter adapter = new TeachersAdapter(teacher);
        recycler.setAdapter(adapter);
    }
}