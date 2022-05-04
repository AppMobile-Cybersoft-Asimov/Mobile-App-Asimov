package com.example.asimov;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.asimov.databinding.ActivityAnnouncementsBinding;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsActivity extends AppCompatActivity {

    private ActivityAnnouncementsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnnouncementsBinding.inflate(getLayoutInflater());
        binding.recyclerData.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false));
        setContentView(binding.getRoot());
        getAnnouncements();
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