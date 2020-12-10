package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recViewSearch1, recViewSearch2;
    EditText editTextSearchPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Turnir");




        recViewSearch1 = findViewById(R.id.recViewSearch1);

        Player player = new Player("ludaci", "teoooo", "vladusić", "2003-06-17", "4");
        Player player1 = new Player("ludaci", "teoooo", "vladusić", "2003-06-17", "4");
        Player player2 = new Player("ludaci", "teoooo", "vladusić", "2003-06-17", "4");
        Player player3 = new Player("ludaci", "teoooo", "vladusić", "2003-06-17", "4");
        Player player4 = new Player("ludaci", "teoooo", "vladusić", "2003-06-17", "4");
        Player player5 = new Player("ludaci", "teoooo", "vladusić", "2003-06-17", "4");

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

        AdapterSearch adapterSearch = new AdapterSearch(this, players);
        recViewSearch1.setAdapter(adapterSearch);
        recViewSearch1.setLayoutManager(new LinearLayoutManager(this));

        editTextSearchPlayers = findViewById(R.id.editTextSearchPlayers);

        editTextSearchPlayers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                //from database get strings that match edittext string player
                //Select * from players where playerName + playerLastName = "player"



            }

            @Override
            public void afterTextChanged(Editable editable) {
                String player_name_last_name = editTextSearchPlayers.getText().toString();
                Integer length = player_name_last_name.length();    //length of string
                ArrayList<Player> players_searched = new ArrayList<>();
                for(Player p : players){
                    //if the string equals to player name or last name send it to adapter
                    if(p.getName().contains(player_name_last_name) || p.getLast_name().contains(player_name_last_name)){
                        players_searched.add(p);
                    }
                }
                recViewSearch2 = findViewById(R.id.recViewSearch2);
                recViewSearch1.setVisibility(View.GONE);
                recViewSearch2.setVisibility(View.VISIBLE);
                AdapterSearch adapterSearch2 = new AdapterSearch(SearchActivity.this, players_searched);
                recViewSearch2.setAdapter(adapterSearch2);
                recViewSearch2.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

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