package com.example.turniraplikacija;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdapterPoluvrijeme extends RecyclerView.Adapter<AdapterPoluvrijeme.ViewHolderPoluvrijeme> {

    Context context;
    Game game;

    public AdapterPoluvrijeme(Context context, Game game) {
        this.context = context;
        this.game = game;
    }

    @NonNull
    @Override
    public ViewHolderPoluvrijeme onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recview_poluvrijeme, parent, false);
        return new ViewHolderPoluvrijeme(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPoluvrijeme.ViewHolderPoluvrijeme holder, int position) {
        ArrayList<GameEvent> gameEvents = game.getGameEvents();
        GameEvent gameEvent = gameEvents.get(position);
        String team1 = game.getTeam1();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("players");

        if(gameEvent.getTeam().equals(team1)){
            holder.tvPlayerTeam2.setVisibility(View.GONE);
            holder.imageViewEventRight.setVisibility(View.GONE);

            holder.tvPlayerTeam1.setVisibility(View.VISIBLE);
            holder.imageViewEventLeft.setVisibility(View.VISIBLE);


            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{

                        Player player = snapshot.child(gameEvent.getScorerID() + "").getValue(Player.class);
                        holder.tvPlayerTeam1.setText(player.getName() + " " + player.getLast_name() + " (" + player.getNumber() + ")");

                        holder.tvPlayerTeam1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, PlayerActivity.class);
                                intent.putExtra("player" , player);
                                context.startActivity(intent);
                            }
                        });

                    }catch (Exception e){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            if(gameEvent.getEvent().equals("Gol")){
                holder.imageViewEventLeft.setImageResource(R.drawable.ball);
            }else if(gameEvent.getEvent().equals("Crveni karton")){
                holder.imageViewEventLeft.setImageResource(R.drawable.red_card);
            }else{
                holder.imageViewEventLeft.setImageResource(R.drawable.yellow_card);
            }
        }else{
            holder.tvPlayerTeam2.setVisibility(View.VISIBLE);
            holder.imageViewEventRight.setVisibility(View.VISIBLE);

            holder.tvPlayerTeam1.setVisibility(View.GONE);
            holder.imageViewEventLeft.setVisibility(View.GONE);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    try{
                        Player player = snapshot.child(gameEvent.getScorerID() + "").getValue(Player.class);
                        holder.tvPlayerTeam2.setText(player.getName() + " " + player.getLast_name() + " (" + player.getNumber() + ")");

                        holder.tvPlayerTeam2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, PlayerActivity.class);
                                intent.putExtra("player" , player);
                                context.startActivity(intent);
                            }
                        });

                    }catch (Exception e){
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            if(gameEvent.getEvent().equals("Gol")){
                holder.imageViewEventRight.setImageResource(R.drawable.ball);
            }else if(gameEvent.getEvent().equals("Crveni karton")){
                holder.imageViewEventRight.setImageResource(R.drawable.red_card);
            }else{
                holder.imageViewEventRight.setImageResource(R.drawable.yellow_card);
            }
        }



    }

    private void findPlayerByID(long player_id){




    }


    @Override
    public int getItemCount() {
        return game.getGameEvents().size();
    }

    public class ViewHolderPoluvrijeme extends RecyclerView.ViewHolder{

        ImageView imageViewEventRight, imageViewEventLeft;
        TextView tvPlayerTeam1, tvPlayerTeam2;
        public ViewHolderPoluvrijeme(@NonNull View itemView) {
            super(itemView);

            imageViewEventLeft = itemView.findViewById(R.id.imageViewEventLeft);
            imageViewEventRight = itemView.findViewById(R.id.imageViewEventRight);
            tvPlayerTeam1 = itemView.findViewById(R.id.tvPlayerTeam1);
            tvPlayerTeam2 = itemView.findViewById(R.id.tvPlayerTeam2);

        }
    }
}
