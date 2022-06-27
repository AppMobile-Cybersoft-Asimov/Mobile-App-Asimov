package com.example.asimov;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asimov.adapters.AnnouncementAdapter;
import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.Announcement;
import com.example.asimov.data.service.AnnouncementsService;
import com.example.asimov.data.service.DirectorService;
import com.example.asimov.databinding.ActivityAnnouncementsBinding;
import com.example.asimov.manager.SessionManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementsActivity extends Fragment {

    private ActivityAnnouncementsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnnouncementsBinding.inflate(getLayoutInflater());
        if (getActivity().getIntent().getStringExtra("userType").equals("profesor")) {
            binding.layoutPublish.setVisibility(View.GONE);
        }
        binding.publishCard.btnPublish.setOnClickListener(v -> publishAnnouncement());
        binding.recyclerData.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getAnnouncements();
        return binding.getRoot();
    }

    private void publishAnnouncement() {
        Announcement newAnnouncement = new Announcement()
                .setTitle(binding.publishCard.txtTitle.getText().toString())
                .setDescription(binding.publishCard.txtTitle.getText().toString())
                .setDirectorId(SessionManager.getInstance().getUserId());
        System.out.println(newAnnouncement.getId());
        System.out.println(newAnnouncement.getDirectorId());
        System.out.println(newAnnouncement.getDescription());
        System.out.println(newAnnouncement.getTitle());
        DirectorService directorService = RetrofitClient.createInstance().create(DirectorService.class);
        directorService.publishAnnouncement(newAnnouncement).enqueue(new Callback<Announcement>() {
            @Override
            public void onResponse(Call<Announcement> call, Response<Announcement> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error creating Announcement", Toast.LENGTH_SHORT).show();
                    return;
                }
                Announcement createdAnnouncement = response.body();
                Toast.makeText(getContext(), "Anuncion creado correctamente", Toast.LENGTH_SHORT).show();
                getAnnouncements();
            }

            @Override
            public void onFailure(Call<Announcement> call, Throwable t) {
                Toast.makeText(getContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getAnnouncements() {
        AnnouncementsService announcementsService = RetrofitClient.createInstanceWithoutToken().create(AnnouncementsService.class);
        announcementsService.getAnnouncements().enqueue(new Callback<List<Announcement>>() {
            @Override
            public void onResponse(Call<List<Announcement>> call, Response<List<Announcement>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Error loading Announcement" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Announcement> announcements = response.body();
                System.out.println(announcements);

                AnnouncementAdapter adapter = new AnnouncementAdapter(announcements,
                        !getActivity().getIntent().getStringExtra("userType").equals("profesor"), AnnouncementsActivity.this);
                binding.recyclerData.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Announcement>> call, Throwable t) {
                Toast.makeText(getContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
            }
        });


    }
}