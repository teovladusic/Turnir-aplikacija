package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView cardViewPrijaviSe;
    CardView cardViewRezultati;
    CardView cardViewRaspored;
    CardView cardViewTablica;
    CardView cardViewStrijelci;
    CardView cardViewSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO); //DISABLE NIGHT MODE


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


        //start RezultatiActivity on cardViewRezultati click

        cardViewRezultati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RezultatiActivity.class);
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
        cardViewRezultati = findViewById(R.id.cardViewRezultati);
        cardViewTablica = findViewById(R.id.cardViewTablica);
        cardViewStrijelci = findViewById(R.id.cardViewStrijelci);
        cardViewSettings = findViewById(R.id.cardViewSettings);
    }

}