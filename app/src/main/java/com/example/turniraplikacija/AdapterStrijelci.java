package com.example.turniraplikacija;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterStrijelci extends RecyclerView.Adapter<AdapterStrijelci.ViewHolderStrijelci>{

    Context context;
    ArrayList<Player> players;

    public AdapterStrijelci(Context context, ArrayList<Player> player) {
        this.context = context;
        this.players = player;
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
        Player player = players.get(position);
        holder.tvStrijelac.setText(player.getName() + " " + player.getLast_name());
        holder.tvGoals.setText(player.getGoals() + "");
        holder.tvRedniBroj.setText((position + 1) + ".");

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public class ViewHolderStrijelci extends RecyclerView.ViewHolder{

        TextView tvStrijelac, tvGoals, tvRedniBroj;

        public ViewHolderStrijelci(@NonNull View itemView) {
            super(itemView);

            tvStrijelac = itemView.findViewById(R.id.tvStrijelac);
            tvGoals = itemView.findViewById(R.id.tvGoals);
            tvRedniBroj = itemView.findViewById(R.id.tvRedniBroj);
        }
    }

}
