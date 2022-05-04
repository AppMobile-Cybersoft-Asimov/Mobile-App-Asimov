package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asimov.databinding.ActivityAnnouncementsBinding;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsActivity extends Fragment {

    private ActivityAnnouncementsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnnouncementsBinding.inflate(getLayoutInflater());
        binding.recyclerData.setLayoutManager(new LinearLayoutManager(
                getContext(), LinearLayoutManager.VERTICAL, false));
        getAnnouncements();
        return binding.getRoot();
    }

    private void getAnnouncements() {
        List<Announcement> announcements = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            announcements.add(new Announcement((long) i, "Title " + i, "Description " + i));
        }
        AnnouncementAdapter adapter = new AnnouncementAdapter(announcements);
        binding.recyclerData.setAdapter(adapter);
    }
}