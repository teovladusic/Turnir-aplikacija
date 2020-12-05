package com.example.turniraplikacija;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdapterStrijelci extends RecyclerView.Adapter<AdapterStrijelci.ViewHolderStrijelci>{

    Context context;
    ArrayList<Player> player;

    public AdapterStrijelci(Context context, ArrayList<Player> player) {
        this.context = context;
        this.player = player;
    }

    @NonNull
    @Override
    public ViewHolderStrijelci onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recview_strijelci, parent, false);
        return new ViewHolderStrijelci(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderStrijelci holder, int position) {
        String scorer = player.get(position).getName() + " " + player.get(position).getLast_name() + " (" + player.get(position).getTeam_name() + ")";
        Integer goal = player.get(position).getGoals();
        holder.tvStrijelac.setText(scorer);
        holder.tvGoals.setText(goal + "");

    }

    @Override
    public int getItemCount() {
        return player.size();
    }

    public class ViewHolderStrijelci extends RecyclerView.ViewHolder{

        TextView tvStrijelac, tvGoals;

        public ViewHolderStrijelci(@NonNull View itemView) {
            super(itemView);

            tvStrijelac = itemView.findViewById(R.id.tvStrijelac);
            tvGoals = itemView.findViewById(R.id.tvGoals);
        }
    }

}
