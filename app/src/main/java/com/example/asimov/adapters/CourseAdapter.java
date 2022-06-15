package com.example.asimov.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimov.R;

import java.util.List;
import com.example.asimov.data.model.Courses;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.viewHolder> {

    private List<Courses> data;
    public CourseAdapter(List<Courses> data) {this.data = data;}

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.frag_course,null, false);

        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.asignData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView id, name, description, state;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            //id = itemView.findViewById(R.id.txtCId);
            name = itemView.findViewById(R.id.txtCName);
            //description = itemView.findViewById(R.id.txtCDescription);
            state = itemView.findViewById(R.id.txtCState);
        }


        public void asignData(Courses courses) {
            //id.setText(courses.getId()+"");
            name.setText("Course: " + courses.getName());
            //description.setText(courses.getDescription()+"");
            if (courses.getState()) {
                state.setText("Completed");
            }
            else {
                state.setText("Incomplete");
            }
        }
    }
}
