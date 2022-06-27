package com.example.asimov.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asimov.R;

import java.util.List;

import com.example.asimov.data.model.Competence;

public class ListCompetencesAdapter extends RecyclerView.Adapter<ListCompetencesAdapter.viewHolder> {

    private final List<Competence> data;

    public ListCompetencesAdapter(List<Competence> data){
        this.data=data;
    }

    @NonNull
    @Override
    public ListCompetencesAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_competence, null, false);

        view.setLayoutParams(new RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT));

        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListCompetencesAdapter.viewHolder holder, int position) {
        holder.asignData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.lblTitle);
            description = itemView.findViewById(R.id.lblDescription);
        }
        public void asignData(Competence competence){
            title.setText("Title: "+ competence.getTitle()+"");
            description.setText("Description: "+ competence.getDescription()+"");
        }
    }
}
