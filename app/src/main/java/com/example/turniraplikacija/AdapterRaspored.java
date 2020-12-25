package com.example.turniraplikacija;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRaspored extends RecyclerView.Adapter<AdapterRaspored.ViewHolderRaspored> {

    Context context;
    ArrayList<Game> games;

    public AdapterRaspored(Context context, ArrayList<Game> games) {
        this.context = context;
        this.games = games;
    }

    @NonNull
    @Override
    public ViewHolderRaspored onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recview_raspored, parent, false);
        return new ViewHolderRaspored(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRaspored holder, int position) {
        Game game = games.get(position);
        holder.tvTeam1.setText(game.getTeam1());
        holder.tvTeam2.setText(game.getTeam2());

        if(game.isPlayed()){
            holder.tvStartTime.setVisibility(View.GONE);
            holder.tvTeam1Goals.setVisibility(View.VISIBLE);
            holder.tvTeam2Goals.setVisibility(View.VISIBLE);
            holder.tvTeam1Goals.setText(game.getTeam1Goals());
            holder.tvTeam2Goals.setText(game.getTeam2Goals());
        }else{
            holder.tvTeam1Goals.setVisibility(View.GONE);
            holder.tvTeam2Goals.setVisibility(View.GONE);
            holder.tvStartTime.setVisibility(View.VISIBLE);
            holder.tvStartTime.setText(game.getTime());
        }

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PrikazUtakmiceActivity.class);
                try{
                    intent.putExtra("game", game);
                    context.startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return games.size();
    }



    public class ViewHolderRaspored extends RecyclerView.ViewHolder{

        TextView tvTeam1, tvTeam2, tvTeam1Goals, tvTeam2Goals, tvStartTime;
        ConstraintLayout constraintLayout;

        public ViewHolderRaspored(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);

            tvTeam1 = itemView.findViewById(R.id.tvPlayerTeam1);
            tvTeam2 = itemView.findViewById(R.id.tvPlayerTeam2);
            tvTeam1Goals = itemView.findViewById(R.id.tvTeam1Goals);
            tvTeam2Goals = itemView.findViewById(R.id.tvTeam2Goals);
            tvStartTime = itemView.findViewById(R.id.tvStartTime);
        }
    }

}
