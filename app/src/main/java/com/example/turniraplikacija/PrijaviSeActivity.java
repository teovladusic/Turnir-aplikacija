package com.example.turniraplikacija;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class PrijaviSeActivity extends AppCompatActivity {

    Button buttonRegister;
    EditText editTextTeamName;
    long maxID = 0;
    private int team_number;

    public static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijavi_se);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Prijavi se");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("teams");
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        team_number = 0;
        team_number = sharedPreferences.getInt("team_number", team_number);


        buttonRegister = findViewById(R.id.buttonRegister);
        editTextTeamName = findViewById(R.id.editTextTeamName);



        //MAKNI CA OVO
        team_number= 1;






        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (team_number == 1) {

                    String team_name = editTextTeamName.getText().toString();
                    if (!team_name.equals("")) {
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot snapshot) {

                                boolean postoji = false;
                                maxID = snapshot.getChildrenCount();

                                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                     postoji = team_name.equals(dataSnapshot.getValue(String.class));
                                }
                                    if (postoji) {
                                        Toast.makeText(PrijaviSeActivity.this, "Ekipa sa istim imenom je vec registrirana", Toast.LENGTH_SHORT).show();
                                    } else {
                                        //PROVJERA JEL VEC REGISTRIRANA EKIPA  //TODO:MAKNI ZAGRADE KAD ZAVRSIS APK I PROMINI UVJET GORI DA TRIBA BIT = 0
                                        //SharedPreferences.Editor editor = sharedPreferences.edit();
                                        //editor.putInt("team_number", team_number);
                                        //editor.commit();
                                        team_number++;
                                        reference.child("" + maxID).setValue(team_name);
                                        Intent intent = new Intent(PrijaviSeActivity.this, RegisterPlayersActivity.class);
                                        intent.putExtra("TEAM_NAME", team_name);
                                        startActivity(intent);
                                    }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } else {
                        Toast.makeText(PrijaviSeActivity.this, "Unesi ime ekipe", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(PrijaviSeActivity.this, "Vec si registrirao ekipu", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }



    //Back button
        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }

            return super.onOptionsItemSelected(item);
        }

        public boolean onCreateOptionsMenu (Menu menu){
            return true;
        }

}
