package com.example.asimov.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimov.data.model.CourseItem;
import com.example.asimov.databinding.CardCourseItemsBinding;

import java.util.List;

public class CourseItemsAdapter extends RecyclerView.Adapter<CourseItemsAdapter.ViewHolder> {

    private final List<CourseItem> data;

    public CourseItemsAdapter(List<CourseItem> data){
        this.data = data;
    }

    @NonNull
    @Override
    public CourseItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardCourseItemsBinding binding = CardCourseItemsBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseItemsAdapter.ViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final CardCourseItemsBinding binding;

        public ViewHolder(@NonNull CardCourseItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(CourseItem courseItem) {
            binding.lblItemName.setText(courseItem.getName());
        }
    }
}
