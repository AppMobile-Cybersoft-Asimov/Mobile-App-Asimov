package com.example.asimov.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.asimov.R;
import com.example.asimov.data.model.Courses;
import com.example.asimov.databinding.ActivityTeacherProfileBinding;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.viewHolder> {

    private final List<Courses> data;
    public CourseAdapter(List<Courses> data) {this.data = data;}

    @NonNull
    @Override
    public CourseAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_course,null, false);
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

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name, description;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtCrsName);
            description = itemView.findViewById(R.id.txtCrsDescription);
        }

        public void setData(Courses courses) {
            name.setText(courses.getName()+"");
            description.setText("Description: "+courses.getDescription()+"");
        }
    }
}
