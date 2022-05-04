package com.example.asimov;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimov.databinding.CardAnnouncementBinding;

import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.ViewHolder> {

    private final List<Announcement> data;

    public AnnouncementAdapter(List<Announcement> data) {
        this.data = data;
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardAnnouncementBinding binding;

        public ViewHolder(@NonNull CardAnnouncementBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Announcement announcement) {
            binding.txtTitle.setText(announcement.getTitle());
            binding.txtDescription.setText(announcement.getDescription());
        }
    }

}
