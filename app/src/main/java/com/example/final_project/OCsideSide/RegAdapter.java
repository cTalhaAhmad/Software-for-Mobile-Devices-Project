package com.example.final_project.OCsideSide;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_project.R;

import java.util.ArrayList;

public class RegAdapter extends RecyclerView.Adapter<RegAdapter.ViewHolder> {
    ArrayList<Reg> mobilesList = new ArrayList<>();

    public RegAdapter(ArrayList<Reg> mobilesList) {
        this.mobilesList = mobilesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reg mobiles = mobilesList.get(position);
        holder.Name.setText(mobiles.getUser());
        holder.event.setText(mobiles.getEvent());
        holder.subevent.setText(mobiles.getSubEvent());
    }

    @Override
    public int getItemCount() {
        return mobilesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Name, event, subevent;

        public ViewHolder(@NonNull View view) {
            super(view);
            Name = view.findViewById(R.id.regtxtname);
            event = view.findViewById(R.id.regtxtevent);
            subevent = view.findViewById(R.id.regtxtsubevent);


        }
    }
}



