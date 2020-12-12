package com.example.turniraplikacija;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AdapterRezultati extends RecyclerView.Adapter<AdapterRezultati.ViewHolderRezultati> {


    Context context;
    ArrayList<Game> games;
    public AdapterRezultati(Context context, ArrayList<Game> games) {
        this.context = context;
        this.games = games;
    }

    @NonNull
    @Override
    public ViewHolderRezultati onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recview_rezultati_and_raspored, parent, false);
        return new ViewHolderRezultati(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRezultati holder, int position) {
        Game game = games.get(position);
        holder.tvTeam1.setText(game.getTeam1());
        holder.tvTeam2.setText(game.getTeam2());
        holder.tvTeam1Goals.setText(game.getTeam1Goals());
        holder.tvTeam2Goals.setText(game.getTeam2Goals());
        holder.tvStartTime.setVisibility(View.GONE);
        holder.tvTeam1Goals.setVisibility(View.VISIBLE);
        holder.tvTeam2Goals.setVisibility(View.VISIBLE);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PrikazUtakmiceActivity.class);
                intent.putExtra("POSITION", position);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return games.size();
    }



    public class ViewHolderRezultati extends RecyclerView.ViewHolder{

        TextView tvTeam1, tvTeam2, tvTeam1Goals, tvTeam2Goals, tvStartTime;
        ConstraintLayout constraintLayout;

        public ViewHolderRezultati(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);

            tvTeam1 = itemView.findViewById(R.id.tvTeam1);
            tvTeam2 = itemView.findViewById(R.id.tvTeam2);
            tvTeam1Goals = itemView.findViewById(R.id.tvTeam1Goals);
            tvTeam2Goals = itemView.findViewById(R.id.tvTeam2Goals);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
        }
    }

}
