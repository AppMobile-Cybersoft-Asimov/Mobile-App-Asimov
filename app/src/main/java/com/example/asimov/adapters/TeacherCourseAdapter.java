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

public class TeacherCourseAdapter extends RecyclerView.Adapter<TeacherCourseAdapter.viewHolder> {

    private List<Courses> data;
    public TeacherCourseAdapter(List<Courses> data) {this.data = data;}

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_teacher_course,null, false);

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
        TextView name, state;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtTCName);
            state = itemView.findViewById(R.id.txtTCState);
        }


        public void asignData(Courses courses) {
            name.setText("Course: " + courses.getName());
            if (courses.getState()) {
                state.setText("Completed");
            }
            else {
                state.setText("Incomplete");
            }
        }
    }
}
