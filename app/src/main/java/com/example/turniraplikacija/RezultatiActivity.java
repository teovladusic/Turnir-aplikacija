package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Objects;

public class RezultatiActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultati);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Turnir");

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<String> domaci = new ArrayList<>();
        domaci.add("hajduk");
        domaci.add("dinamo");
        domaci.add("slaven");

        ArrayList<String> gosti = new ArrayList<>();
        gosti.add("inter");
        gosti.add("reka");
        gosti.add("osijek");

        ArrayList<String> domaci_goals = new ArrayList<>();
        domaci_goals.add("1");
        domaci_goals.add("2");
        domaci_goals.add("3");

        ArrayList<String> gosti_goals = new ArrayList<>();
        gosti_goals.add("1");
        gosti_goals.add("2");
        gosti_goals.add("3");

        AdapterRezultati adapter = new AdapterRezultati(domaci, gosti, this, domaci_goals, gosti_goals);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

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

