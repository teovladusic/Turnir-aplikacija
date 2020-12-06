package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class RasporedActivity extends AppCompatActivity {

    TextView tvTeam1, tvTeam2, tvTeam1Goals, tvTeam2Goals, tvStartTime;
    RecyclerView recViewRaspored;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rasporedi);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        recViewRaspored = findViewById(R.id.recViewRaspored);

        ArrayList<String> domaci = new ArrayList<>();
        domaci.add("hajduk");
        domaci.add("dinamo");
        domaci.add("slaven");

        ArrayList<String> gosti = new ArrayList<>();
        gosti.add("inter");
        gosti.add("reka");
        gosti.add("osijek");

        ArrayList<String> start_time = new ArrayList<>();
        start_time.add("14:00");
        start_time.add("15:00");
        start_time.add("16:00");

        AdapterRaspored adapterRaspored = new AdapterRaspored(domaci, gosti, this, start_time);
        recViewRaspored.setAdapter(adapterRaspored);
        recViewRaspored.setLayoutManager(new LinearLayoutManager(this));

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