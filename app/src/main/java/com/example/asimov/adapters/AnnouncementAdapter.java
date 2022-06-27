package com.example.asimov.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimov.AnnouncementsActivity;
import com.example.asimov.data.RetrofitClient;
import com.example.asimov.data.model.Announcement;
import com.example.asimov.data.service.AnnouncementsService;
import com.example.asimov.databinding.CardAnnouncementBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {

    private final List<Announcement> data;
    private final Boolean showDelete;
    private final AnnouncementsActivity activity;

    public AnnouncementAdapter(List<Announcement> data, boolean showDelete, AnnouncementsActivity activity) {
        this.data = data;
        this.showDelete = showDelete;
        this.activity = activity;
    }

    @NonNull
    @Override
    public AnnouncementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardAnnouncementBinding binding = CardAnnouncementBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CardAnnouncementBinding binding;

        public ViewHolder(@NonNull CardAnnouncementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Announcement announcement) {
            binding.txtTitle.setText(announcement.getTitle());
            binding.txtDescription.setText(announcement.getDescription());
            if (!showDelete) {
                binding.btnDelete.setVisibility(View.GONE);
            }
            binding.btnDelete.setOnClickListener(view -> deleteAnnouncement(announcement.getId()));
        }

        private void deleteAnnouncement(Long id) {
            AnnouncementsService announcementsService = RetrofitClient.createInstanceWithoutToken().create(AnnouncementsService.class);
            announcementsService.deleteAnnouncement(id).enqueue(new Callback<Announcement>() {
                @Override
                public void onResponse(Call<Announcement> call, Response<Announcement> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(activity.getContext(), "Error loading Announcement" + response.errorBody(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    activity.getAnnouncements();
                    Toast.makeText(activity.getContext(), "Anuncio Eliminado con exito" + response.errorBody(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<Announcement> call, Throwable t) {
                    Toast.makeText(activity.getContext(), "Error connecting to the server", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
