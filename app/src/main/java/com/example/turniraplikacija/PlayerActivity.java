package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class PlayerActivity extends AppCompatActivity {

    TextView textVName, tvTeam, tvNumber, tvYellowCardsNumber, tvRedCardsNumber, tvNumberOfGames, tvBirthDate, tvGolovi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pregled igrača");

        textVName = findViewById(R.id.textVName);
        tvTeam = findViewById(R.id.tvTeam);
        tvNumber = findViewById(R.id.tvNumber);
        tvYellowCardsNumber = findViewById(R.id.tvYellowCardsNumber);
        tvRedCardsNumber = findViewById(R.id.tvRedCardsNumber);
        tvNumberOfGames = findViewById(R.id.tvNumberOfGames);
        tvBirthDate = findViewById(R.id.tvBirthDate);
        tvGolovi = findViewById(R.id.tvGolovi);

        Intent intent = getIntent();
        //strijelci

        Player player = new Player();

        try {
            player = (Player) intent.getSerializableExtra("player");
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        textVName.setText(player.getName() + " " + player.getLast_name());
        tvTeam.setText(player.getTeam_name());
        tvNumber.setText(player.getNumber());
        tvGolovi.setText(player.getGoals() + "");
        tvYellowCardsNumber.setText(player.getYellow_cards() + "");
        tvRedCardsNumber.setText(player.getRed_cards() + "");
        tvNumberOfGames.setText(player.getNumber_of_games() + "");

        String date = player.getDate();
        String day = date.substring(8,10);
        String month = date.substring(5,7);
        String year = date.substring(0,4);
        tvBirthDate.setText(day + "." + month + "." + year + ".");

    }

    //Back button
    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        return true;
    }
}