package com.example.turniraplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class RasporedActivity extends AppCompatActivity {

    RecyclerView recViewRaspored;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    ProgressBar progressBarRaspored;
    Spinner spinnerCalendar;
    TextView tvDate, tvNoGames;
    String date;
    int index;  //position of selected item (spinner)
    String queryDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raspored);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Raspored");

        recViewRaspored = findViewById(R.id.recViewRaspored);
        progressBarRaspored = findViewById(R.id.progressBarRaspored);
        progressBarRaspored.setVisibility(View.VISIBLE);
        tvDate = findViewById(R.id.tvDate);
        spinnerCalendar = findViewById(R.id.spinnerCalendar);
        tvNoGames = findViewById(R.id.tvNoGames);

        LocalDate localDate = LocalDate.now();
        ArrayList<String> dates = new ArrayList<>();
        dates.add(formDateString(localDate.minusDays(3)));
        dates.add(formDateString(localDate.minusDays(2)));
        dates.add(formDateString(localDate.minusDays(1)));
        dates.add("DANAS");
        dates.add(formDateString(localDate.plusDays(1)));
        dates.add(formDateString(localDate.plusDays(2)));
        dates.add(formDateString(localDate.plusDays(3)));

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, dates);
        spinnerCalendar.setAdapter(adapter);
        spinnerCalendar.setSelection(3);

        date = localDate.toString();
        tvDate.setText(formDateString(localDate));

        ArrayList<Game> games = new ArrayList<>();
        DatabaseReference reference = database.getReference().child("games");

        spinnerCalendar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LocalDate localDate = LocalDate.now();
                queryDate = spinnerCalendar.getSelectedItem().toString();
                index = spinnerCalendar.getSelectedItemPosition();
                tvDate.setText(queryDate);
                if(index < 3){
                    index -= 3;
                    queryDate = localDate.minusDays(Math.abs(index)).toString();
                }else if(index == 3){
                    queryDate = localDate.toString();
                }else{
                    index -= 3;
                    queryDate = localDate.plusDays(Math.abs(index)).toString();
                }

                //query
                Query query = reference.orderByChild("date").equalTo(queryDate);
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        tvNoGames.setVisibility(View.INVISIBLE);
                        try {
                            games.clear();
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                Game game = dataSnapshot.getValue(Game.class);
                                games.add(game);
                            }
                            progressBarRaspored.setVisibility(View.GONE);
                            AdapterRaspored adapter = new AdapterRaspored(RasporedActivity.this, games);
                            recViewRaspored.setAdapter(adapter);
                            recViewRaspored.setLayoutManager(new LinearLayoutManager(RasporedActivity.this));
                            if(games.size() == 0){
                                tvNoGames.setVisibility(View.VISIBLE);
                            }
                        }catch (Throwable e){
                            Toast.makeText(RasporedActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private String formDateString(LocalDate localDate){
        String dayOfWeek = localDate.getDayOfWeek().toString();
        dayOfWeek = translateToHrv(dayOfWeek);
        String day = localDate.toString().substring(8,10);
        String month = localDate.toString().substring(5,7);
        String year = localDate.toString().substring(0,4);

        return dayOfWeek + ", " + day + "." + month + "." + year + ".";
    }

    private String translateToHrv(String dayofWeek){
        switch (dayofWeek.toLowerCase()){
            case "monday":
                dayofWeek = "Ponedjeljak";
                break;
            case "tuesday":
                dayofWeek = "Utorak";
                break;
            case "wednesday":
                dayofWeek = "Srijeda";
                break;
            case "thursday":
                dayofWeek = "Četvrtak";
                break;
            case "friday":
                dayofWeek = "Petak";
                break;
            case "saturday":
                dayofWeek = "Subota";
                break;
            default:
                dayofWeek = "Nedjelja";
                break;
        }
        return  dayofWeek;
    }

    //Back navigation bar button and calendar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }else if(item.getItemId() == R.id.btnCalendar){
            spinnerCalendar.performClick();

        }

        return super.onOptionsItemSelected(item);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);

        return super.onCreateOptionsMenu(menu);

    }
}