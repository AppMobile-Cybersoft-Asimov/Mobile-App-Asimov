package com.example.asimov;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimov.data.model.Teacher;

import java.util.List;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.viewHolder> {

    private List<Teacher> data;

    public TeachersAdapter(List<Teacher> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_teacher, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.asignarData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView id, name, email, phone;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            //id = itemView.findViewById(R.id.cardIdTeachers);
            name= itemView.findViewById(R.id.cardTeacherTxtIdName);
            email= itemView.findViewById(R.id.cardTeacherTxtIdEmail);
            phone= itemView.findViewById(R.id.cardTeacherTxtIdPhone);
        }

        public void asignarData( Teacher teacher) {
            name.setText(teacher.getName()+"");
            email.setText("Email: " + teacher.getEmail()+"");
            phone.setText("Name: " + teacher.getPhone()+"");
        }
    }
}
