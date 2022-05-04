package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asimov.databinding.ActivityDashboardDirectorBinding;

public class DashboardDirectorActivity extends Fragment {

    private ActivityDashboardDirectorBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardDirectorBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

}