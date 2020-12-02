package com.example.turniraplikacija;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PrijaviSeActivity extends AppCompatActivity {

    Button buttonRegister;
    EditText editTextTeamName;
    String team_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijavi_se);

        buttonRegister = findViewById(R.id.buttonRegister);
        editTextTeamName = findViewById(R.id.editTextTeamName);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                team_name = editTextTeamName.getText().toString();

                if(team_name.equals("")){
                    Toast.makeText(PrijaviSeActivity.this, "Ime ekipe ne moze biti prazno", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(PrijaviSeActivity.this, RegisterPlayersActivity.class);
                    intent.putExtra("TEAM_NAME", team_name);
                    startActivity(intent);
                }
            }
        });
    }

}