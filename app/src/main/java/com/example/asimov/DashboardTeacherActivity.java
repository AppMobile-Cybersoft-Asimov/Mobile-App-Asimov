package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asimov.databinding.ActivityDashboardDirectorBinding;
import com.example.asimov.databinding.ActivityDashboardTeacherBinding;

public class DashboardTeacherActivity extends Fragment {

    private ActivityDashboardTeacherBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardTeacherBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

}