package com.example.turniraplikacija;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prijavi_se);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Turnir");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


        buttonRegister = findViewById(R.id.buttonRegister);
        editTextTeamName = findViewById(R.id.editTextTeamName);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String team_name = editTextTeamName.getText().toString();
                if(!team_name.equals("")) {
                    reference.child("ekipe").child(team_name).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.getValue() != null) {
                                Toast.makeText(PrijaviSeActivity.this, "Tim je vec registriran ", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(PrijaviSeActivity.this, RegisterPlayersActivity.class);
                                intent.putExtra("TEAM_NAME", team_name);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }else{
                    Toast.makeText(PrijaviSeActivity.this, "Unesi ime ekipe", Toast.LENGTH_SHORT).show();
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
