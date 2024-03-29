package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    //TODO: NAPRAVI U ADMIN APLIKACIJI DODAVANJE EVENTA
    //TODO: PRIKAZ IGRACA

    CardView cardViewPrijaviSe;
    CardView cardViewShowTeams;
    CardView cardViewRaspored;
    CardView cardViewTablica;
    CardView cardViewStrijelci;
    CardView cardViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //DISABLE NIGHT MODE
        getSupportActionBar().setTitle("Turnir");

        //start SearchActivity on cardViewSearch click
        cardViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        //start StrijelciActivity on cardViewActivity click

        cardViewStrijelci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StrijelciActivity.class);
                startActivity(intent);
            }
        });

        //start RasporedActivity on cardViewRaspored click

        cardViewRaspored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RasporedActivity.class);
                startActivity(intent);
            }
        });


        //start RasporedActivity on cardViewRaspored click

        cardViewShowTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ShowTeamsActivity.class);
                startActivity(intent);
            }
        });



        //start PrijaviSeActivity on cardViewPrijaviSe click

        cardViewPrijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PrijaviSeActivity.class);
                startActivity(intent);
            }
        });



    }




    private void assignViews(){
        cardViewPrijaviSe = findViewById(R.id.cardViewPrijaviSe);
        cardViewRaspored = findViewById(R.id.cardViewRaspored);
        cardViewShowTeams = findViewById(R.id.cardViewShowTeams);
        cardViewTablica = findViewById(R.id.cardViewTablica);
        cardViewStrijelci = findViewById(R.id.cardViewStrijelci);
        cardViewSearch = findViewById(R.id.cardViewSearch);
    }

}