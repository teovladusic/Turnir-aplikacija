package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RezultatiActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rezultati);

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<String> domaci = new ArrayList<>();
        domaci.add("hajduk");
        domaci.add("dinamo");
        domaci.add("slaven");

        ArrayList<String> gosti = new ArrayList<>();
        gosti.add("inter");
        gosti.add("reka");
        gosti.add("osijek");

        MyAdapter adapter = new MyAdapter(domaci, gosti, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }
}