package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class StrijelciActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strijelci);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewStrijelci);

        Player player = new Player("ludaci", "teo", "vladusic", "17", "6", "2003", "4");
        Player player1 = new Player("ludaci", "lucija", "anteric", "17", "6", "2003", "4");
        Player player2 = new Player("ludaci", "stipe", "vujevic", "17", "6", "2003", "4");
        Player player3 = new Player("ludaci", "kante", "koronic", "17", "6", "2003", "4");
        Player player4 = new Player("ludaci", "luka", "lijic", "17", "6", "2003", "4");
        Player player5 = new Player("ludaci", "ponja", "ponja", "17", "6", "2003", "4");

        player.setGoals(0);
        player1.setGoals(1);
        player2.setGoals(2);
        player3.setGoals(3);
        player4.setGoals(4);
        player5.setGoals(5);

        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);

        AdapterStrijelci adapterStrijelci = new AdapterStrijelci(this, players);
        recyclerView.setAdapter(adapterStrijelci);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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