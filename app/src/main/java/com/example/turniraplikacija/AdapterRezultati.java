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

import java.util.ArrayList;

public class AdapterRezultati extends RecyclerView.Adapter<AdapterRezultati.ViewHolderRezultati> {

    ArrayList<String> domaci;
    ArrayList<String> gosti;
    Context context;
    ArrayList<String> domaci_goals;
    ArrayList<String> gosti_goals;

    public AdapterRezultati(ArrayList<String> domaci, ArrayList<String> gosti, Context context, ArrayList<String> domaci_goals, ArrayList<String> gosti_goals) {
        this.domaci = domaci;
        this.gosti = gosti;
        this.context = context;
        this.domaci_goals = domaci_goals;
        this.gosti_goals = gosti_goals;
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
        holder.tvTeam1.setText(domaci.get(position));
        holder.tvTeam2.setText(gosti.get(position));
        holder.tvTeam1Goals.setText(domaci_goals.get(position));
        holder.tvTeam2Goals.setText(gosti_goals.get(position));
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
        return domaci.size();
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
