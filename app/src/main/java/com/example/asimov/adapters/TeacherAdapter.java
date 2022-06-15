package com.example.asimov.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimov.R;

import java.util.List;

import com.example.asimov.data.model.Teachers;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.viewHolder> {
    
    List<Teachers> data;
    public TeacherAdapter(List<Teachers> data) {this.data = data;}
    
    @NonNull
    @Override
    public TeacherAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_teacher_profile,
                null, false);
        return new TeacherAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapter.viewHolder holder, int position) {
        holder.asignData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView id, firstName, lastName, age, email, phone, point, directorId;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            //id = itemView.findViewById(R.id.txtTId);
            firstName = itemView.findViewById(R.id.txtTFirstName);
            lastName = itemView.findViewById(R.id.txtTLastName);
            age = itemView.findViewById(R.id.txtTAge);
            email = itemView.findViewById(R.id.txtTEmail);
            phone = itemView.findViewById(R.id.txtTPhone);
            point = itemView.findViewById(R.id.txtTPoint);
            //directorId = itemView.findViewById(R.id.txtTDirectorId);
        }

        public void asignData(Teachers teachers) {
            //id.setText(teachers.getId()+"");
            firstName.setText(teachers.getFirstName()+"");
            lastName.setText(teachers.getLastName()+"");
            age.setText(teachers.getAge()+"");
            email.setText(teachers.getEmail()+"");
            phone.setText(teachers.getPhone()+"");
            point.setText(teachers.getPoint()+"");
            //directorId.setText(teachers.getDirectorId()+"");
        }
    }
}
