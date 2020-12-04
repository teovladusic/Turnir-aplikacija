package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class PrikazUtakmiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prikaz_utakmice);

        Intent intent = getIntent();
        int position = intent.getIntExtra("POSITION", 99);
        Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();

    }
}