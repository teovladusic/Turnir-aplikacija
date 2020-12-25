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

public class AdapterSearch extends RecyclerView.Adapter<AdapterSearch.ViewHolderSearch> {

    Context context;
    ArrayList<Player> igraci;

    public AdapterSearch(Context context, ArrayList<Player> igraci) {
        this.context = context;
        this.igraci = igraci;
    }

    @NonNull
    @Override
    public AdapterSearch.ViewHolderSearch onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recview_igrac, parent, false);
        return new ViewHolderSearch(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSearch.ViewHolderSearch holder, int position) {
        Player igrac = igraci.get(position);
        String NameLastName = igrac.getName() + " " + igrac.getLast_name();
        holder.tvNameLastName.setText(NameLastName);
        holder.tvTeam.setText(igrac.getTeam_name());
        holder.tvGoalsNum.setText(igrac.getGoals() + "");

        holder.searchParentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerActivity.class);
                intent.putExtra("player", igrac);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return igraci.size();
    }

    public class ViewHolderSearch extends RecyclerView.ViewHolder{

        TextView tvNameLastName, tvTeam, tvNumGoals, tvGoalsNum;
        ConstraintLayout searchParentLayout;
        public ViewHolderSearch(@NonNull View itemView) {
            super(itemView);

            tvNameLastName = itemView.findViewById(R.id.tvNameLastName);
            tvGoalsNum = itemView.findViewById(R.id.tvGoalsNum);
            tvTeam = itemView.findViewById(R.id.tvTeam);
            tvNumGoals = itemView.findViewById(R.id.tvNumGoals);
            searchParentLayout = itemView.findViewById(R.id.searchParentLayout);
        }
    }
}
