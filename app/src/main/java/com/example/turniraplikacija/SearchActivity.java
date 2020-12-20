package com.example.turniraplikacija;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recViewSearch;
    SearchView searchView;
    ProgressBar progressBarSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pretrazi");

        recViewSearch = findViewById(R.id.recViewSearch);

        ArrayList<Player> players = new ArrayList<>();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("players");
        searchView = findViewById(R.id.searchView);
        progressBarSearch = findViewById(R.id.progressBarSearch);
        searchView.setQueryHint("Pretraži igrače");

        ArrayList<Player> filteredList = new ArrayList<>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                progressBarSearch.setVisibility(View.VISIBLE);
                players.clear();
                try {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        players.add(dataSnapshot.getValue(Player.class));
                    }
                    progressBarSearch.setVisibility(View.GONE);
                    AdapterSearch adapterSearch = new AdapterSearch(SearchActivity.this, players);
                    recViewSearch.setAdapter(adapterSearch);
                    recViewSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                }catch (Exception e){
                    Toast.makeText(SearchActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SearchActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



        if (searchView != null) {
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filteredList.clear();
                    for (Player p : players){
                        String name_last_name = p.getName().toLowerCase() + " " + p.getLast_name().toLowerCase();
                        if(name_last_name.toLowerCase().contains(newText.toLowerCase())){
                            filteredList.add(p);
                        }
                    }
                    AdapterSearch adapterSearch1 = new AdapterSearch(SearchActivity.this , filteredList);
                    recViewSearch.setAdapter(adapterSearch1);
                    adapterSearch1.notifyDataSetChanged();
                    recViewSearch.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                    return true;
                }
            });
        }


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