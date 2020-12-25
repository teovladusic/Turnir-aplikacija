package com.example.turniraplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class PrikazUtakmiceActivity extends AppCompatActivity {

    RecyclerView recViewEvents;
    TextView textVTeam1, textVTeam2, textVteam1Goals, textVteam2Goals, tvDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_utakmice);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Utakmica");
        assignViews();

        Intent intent = getIntent();
        Game game = (Game) intent.getSerializableExtra("game");

        textVTeam1.setText(game.getTeam1());
        textVTeam2.setText(game.getTeam2());
        textVteam1Goals.setText(game.getTeam1Goals());
        textVteam2Goals.setText(game.getTeam2Goals());
        String date = game.getDate();
        String day = date.substring(8,10);
        String month = date.substring(5,7);
        String year = date.substring(0,4);
        tvDateTime.setText(day + "." + month + "." + year + "." + "  " + game.getTime());


        AdapterPoluvrijeme adapterPoluvrijeme = new AdapterPoluvrijeme(this, game);
        recViewEvents.setAdapter(adapterPoluvrijeme);
        recViewEvents.setLayoutManager(new LinearLayoutManager(this));
    }

    private void assignViews(){
        recViewEvents = findViewById(R.id.recViewEvents);
        textVTeam1 = findViewById(R.id.textVTeam1);
        textVTeam2 = findViewById(R.id.textVTeam2);
        textVteam1Goals = findViewById(R.id.textVteam1Goals);
        textVteam2Goals = findViewById(R.id.textVteam2Goals);
        tvDateTime = findViewById(R.id.tvDateTime);
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