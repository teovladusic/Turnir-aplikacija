package com.example.turniraplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class StrijelciActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strijelci);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Najbolji strijelci");

        progressBar = findViewById(R.id.progressBarStrijelci);


        recyclerView = findViewById(R.id.recyclerViewStrijelci);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("players");
        ArrayList<Player> players = new ArrayList<>();
        progressBar.setVisibility(View.VISIBLE);



        Query query = reference.orderByChild("goals").limitToLast(10);


        AdapterStrijelci adapterStrijelci = new AdapterStrijelci(this, players);
        recyclerView.setAdapter(adapterStrijelci);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                players.clear();
                if(snapshot.exists()){
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        progressBar.setVisibility(View.VISIBLE);
                        Player player = dataSnapshot.getValue(Player.class);
                        players.add(player);
                    }

                }
                Collections.reverse(players);
                adapterStrijelci.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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