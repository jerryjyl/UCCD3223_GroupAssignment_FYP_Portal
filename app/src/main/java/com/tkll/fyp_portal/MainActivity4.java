package com.tkll.fyp_portal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar; // Import Toolbar
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity4 extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter3 mainAdapter3;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel3> options =
                new FirebaseRecyclerOptions.Builder<MainModel3>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FYP Student List"), MainModel3.class)
                        .build();

        mainAdapter3 = new MainAdapter3(options);
        recyclerView.setAdapter(mainAdapter3);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddActivity3.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter3.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter3.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                txtSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                txtSearch(query);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void txtSearch(String str) {
        FirebaseRecyclerOptions<MainModel3> options =
                new FirebaseRecyclerOptions.Builder<MainModel3>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FYP Student List").orderByChild("StudentName").startAt(str).endAt(str + "~"), MainModel3.class)
                        .build();

        mainAdapter3.updateOptions(options); // Update adapter options
    }
}

