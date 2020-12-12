package com.example.turniraplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class RasporedActivity extends AppCompatActivity {

    RecyclerView recViewRaspored;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rasporedi);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Turnir");

        recViewRaspored = findViewById(R.id.recViewRaspored);


        ArrayList<Game> games = new ArrayList<>();
        DatabaseReference reference = database.getReference().child("utakmice").child("neodigrane");



        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                try {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                        Game game = dataSnapshot.getValue(Game.class);
                        Toast.makeText(RasporedActivity.this, game.getTeam1(), Toast.LENGTH_SHORT).show();
                        games.add(game);
                    }
                    AdapterRaspored adapter = new AdapterRaspored(RasporedActivity.this, games);
                    recViewRaspored.setAdapter(adapter);
                    recViewRaspored.setLayoutManager(new LinearLayoutManager(RasporedActivity.this));
                }catch (Throwable e){
                    Toast.makeText(RasporedActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
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