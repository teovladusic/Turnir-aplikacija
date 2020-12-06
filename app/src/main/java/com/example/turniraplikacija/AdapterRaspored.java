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

public class AdapterRaspored extends RecyclerView.Adapter<AdapterRaspored.ViewHolderRaspored> {

    ArrayList<String> domaci;
    ArrayList<String> gosti;
    Context context;
    ArrayList<String> start_time;

    public AdapterRaspored(ArrayList<String> domaci, ArrayList<String> gosti, Context context, ArrayList<String> start_time) {
        this.domaci = domaci;
        this.gosti = gosti;
        this.context = context;
        this.start_time = start_time;
    }

    @NonNull
    @Override
    public ViewHolderRaspored onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recview_rezultati_and_raspored, parent, false);
        return new ViewHolderRaspored(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRaspored holder, int position) {
        holder.tvTeam1.setText(domaci.get(position));
        holder.tvTeam2.setText(gosti.get(position));
        holder.tvTeam1Goals.setVisibility(View.GONE);
        holder.tvTeam2Goals.setVisibility(View.GONE);
        holder.tvStartTime.setVisibility(View.VISIBLE);
        holder.tvStartTime.setText(start_time.get(position));

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



    public class ViewHolderRaspored extends RecyclerView.ViewHolder{

        TextView tvTeam1, tvTeam2, tvTeam1Goals, tvTeam2Goals, tvStartTime;
        ConstraintLayout constraintLayout;

        public ViewHolderRaspored(@NonNull View itemView) {
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
