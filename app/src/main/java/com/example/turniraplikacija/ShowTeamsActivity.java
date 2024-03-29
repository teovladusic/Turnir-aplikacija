package com.example.turniraplikacija;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class ShowTeamsActivity extends AppCompatActivity {

    RecyclerView recyclerViewPlayers, recyclerViewGames;
    Spinner spinnerTeams;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_teams);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pregled ekipa");

        recyclerViewPlayers = findViewById(R.id.recViewPlayers);
        spinnerTeams = findViewById(R.id.spinnerTeams);
        recyclerViewGames = findViewById(R.id.recyclerViewGames);

        DatabaseReference referencePlayers = FirebaseDatabase.getInstance().getReference().child("players");
        DatabaseReference referenceTeams = FirebaseDatabase.getInstance().getReference().child("teams");
        DatabaseReference referenceGames = FirebaseDatabase.getInstance().getReference().child("games");
        ArrayList<String> teams = new ArrayList<>();

        referenceTeams.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    teams.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        teams.add(dataSnapshot.getValue(String.class));
                    }
                    ArrayAdapter<String> adapterTeams = new ArrayAdapter<>(ShowTeamsActivity.this, android.R.layout.simple_spinner_dropdown_item, teams);
                    spinnerTeams.setAdapter(adapterTeams);
                } catch (Exception e) {
                    Toast.makeText(ShowTeamsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShowTeamsActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<Game> allGames = new ArrayList<>();

        referenceGames.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    allGames.clear();

                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Game game = dataSnapshot.getValue(Game.class);
                        allGames.add(game);
                    }

                }catch (Exception e){
                    Toast.makeText(ShowTeamsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        spinnerTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(20);

                String team = spinnerTeams.getSelectedItem().toString();
                ArrayList<Player> players = new ArrayList<>();

                Query query = referencePlayers.orderByChild("team_name").equalTo(team);

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Player player = dataSnapshot.getValue(Player.class);
                            players.add(player);
                        }
                        AdapterSearch adapterSearch = new AdapterSearch(ShowTeamsActivity.this, players);
                        recyclerViewPlayers.setAdapter(adapterSearch);
                        recyclerViewPlayers.setLayoutManager(new LinearLayoutManager(ShowTeamsActivity.this));
                        adapterSearch.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                //FILTRIRAJ UTAKMICE I UBACI IH U ADAPTER RASPORED
                ArrayList<Game> games = new ArrayList<>();

                for(Game g : allGames){
                    if(g.getTeam1().equals(team) || g.getTeam2().equals(team)){
                        games.add(g);
                    }
                }
                AdapterRaspored adapterRaspored = new AdapterRaspored(ShowTeamsActivity.this, games);
                recyclerViewGames.setAdapter(adapterRaspored);
                recyclerViewGames.setLayoutManager(new LinearLayoutManager(ShowTeamsActivity.this));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
    }


    //Back navigation bar button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

}