package com.example.asimov.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimov.CourseInformationActivity;
import com.example.asimov.CoursesList;
import com.example.asimov.R;
import com.example.asimov.data.model.Courses;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.viewHolder> {
    private final List<Courses> data;
    private final RecyclerViewClickListener listener;
    private final CoursesList fragment;

    public CourseAdapter(List<Courses> data, RecyclerViewClickListener listener, CoursesList context) {
        this.data = data;
        this.listener = listener;
        this.fragment = context;
    }

    @NonNull
    @Override
    public CourseAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_course, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.viewHolder holder, int position) {
        holder.setData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name, description;
        Button btnCourseIfo;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtCrsName);
            description = itemView.findViewById(R.id.txtCrsDescription);
            btnCourseIfo = itemView.findViewById(R.id.btnToCourseInfo);
        }

        private void onClick(int id) {
            CourseInformationActivity nextFrag = new CourseInformationActivity();
            Bundle bundle = new Bundle();
            bundle.putString("id", String.valueOf(id));
            nextFrag.setArguments(bundle);
            fragment.getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, nextFrag, "CourseInformationActivity")
                    .addToBackStack(null)
                    .commit();
        }

        public void setData(Courses courses) {
            name.setText(courses.getName() + "");
            description.setText("Description: " + courses.getDescription() + "");
            btnCourseIfo.setOnClickListener(view -> onClick(courses.getId()));
        }

    }
}
