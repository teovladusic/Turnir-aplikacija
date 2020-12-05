package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class RegisterPlayersActivity extends AppCompatActivity {

    Spinner spinnerNumber, spinnerDay, spinnerMonth, spinnerYear;
    EditText editTextName, editTextLastName;
    Button buttonRegisterPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_players);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        assignViews();


        ArrayList<String> days = new ArrayList<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<String> months = new ArrayList<>();
        ArrayList<String> years = new ArrayList<>();

        for (int i = 1; i < 100; i++){
            numbers.add(i);
        }
        for(int i = 0; i < 32; i++){
            days.add(i + "");
        }

        for(int i = 1; i <13; i++){
            months.add(i + "");
        }


        for(int i = 1960; i < 2010; i++){
            years.add(i + "");
        }

        ArrayAdapter<String> adapter_days = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, days);
        ArrayAdapter<String> adapter_months = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, months);
        ArrayAdapter<String> adapter_years = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, years);
        ArrayAdapter<Integer> adapter_numbers = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, numbers);

        spinnerNumber.setAdapter(adapter_numbers);
        spinnerDay.setAdapter(adapter_days);
        spinnerMonth.setAdapter(adapter_months);
        spinnerYear.setAdapter(adapter_years);



        buttonRegisterPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                String team_name = intent.getStringExtra("TEAM_NAME");

                String name = editTextName.getText().toString();
                String last_name = editTextLastName.getText().toString();
                String number = spinnerNumber.getSelectedItem().toString();
                String day = spinnerDay.getSelectedItem().toString();
                String month = spinnerMonth.getSelectedItem().toString();
                String year = spinnerYear.getSelectedItem().toString();

                //Player player = new Player(team_name, name, last_name, day, month, year, number);  //TODO: spremi u bazu objekt
                Toast.makeText(RegisterPlayersActivity.this, "Igrac registriran", Toast.LENGTH_SHORT).show();

                editTextName.setText("");
                editTextLastName.setText("");
                spinnerDay.setSelection(0);
                spinnerMonth.setSelection(0);
                spinnerYear.setSelection(0);
                spinnerNumber.setSelection(0);
            }
        });


    }

    private void assignViews(){
        spinnerNumber = findViewById(R.id.spinnerNumber);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        buttonRegisterPlayers = findViewById(R.id.buttonRegisterPlayer);

        spinnerMonth = findViewById(R.id.spinnerMonth);
        spinnerDay = findViewById(R.id.spinnerDay);
        spinnerYear = findViewById(R.id.spinnerYear);


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