package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    CardView cardViewPrijaviSe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();


        //Create intent and start PrijaviSeActivity on cardViewPrijaviSe click

        Intent intentPrijaviSe = new Intent(this, PrijaviSeActivity.class);
        cardViewPrijaviSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentPrijaviSe);
            }
        });

    }




    private void assignViews(){
        cardViewPrijaviSe = findViewById(R.id.cardViewPrijaviSe);
    }

}