package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterPlayersActivity extends AppCompatActivity {

    Spinner spinnerNumber;
    EditText editTextName, editTextLastName, editTextBirthDate;
    Button buttonRegisterPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_players);
        assignViews();



        ArrayList<Integer> numbers = new ArrayList<>();

        for (int i = 1; i < 100; i++){
            numbers.add(i);
        }

        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item, numbers);
        spinnerNumber.setAdapter(adapter);

        buttonRegisterPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = getIntent();
                String team_name = intent.getStringExtra("TEAM_NAME");

                String name = editTextName.getText().toString();
                String last_name = editTextLastName.getText().toString();
                String number = spinnerNumber.getSelectedItem().toString();

                //Get birth date form editText and covert it to Date
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date birth_date;
                ArrayList<Player> players = new ArrayList<>();  //ArrayList igraca iz jednog tima  //Todo: spremi to u bazu podataka
                try {
                    birth_date = sdf.parse(editTextBirthDate.getText().toString());
                    Player player = new Player(team_name, name, last_name, birth_date, number);
                    players.add(player);
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterPlayersActivity.this, "Krivo si unio datum rodenja", Toast.LENGTH_SHORT).show();
                }
                
            }
        });


    }

    private void assignViews(){
        spinnerNumber = findViewById(R.id.spinnerNumber);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextBirthDate = findViewById(R.id.editTextBirthDate);
        buttonRegisterPlayers = findViewById(R.id.buttonRegisterPlayer);

    }
}