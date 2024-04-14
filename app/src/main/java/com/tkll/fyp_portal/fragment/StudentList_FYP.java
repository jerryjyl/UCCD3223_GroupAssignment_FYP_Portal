package com.tkll.fyp_portal.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;
import com.tkll.fyp_portal.AddActivity;
import com.tkll.fyp_portal.MainAdapter;
import com.tkll.fyp_portal.MainModel;
import com.tkll.fyp_portal.R;

public class StudentList_FYP extends Fragment {
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    FloatingActionButton floatingActionButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_studentlist_fyp, container, false);

        // Setup Toolbar
        Toolbar toolbar = rootView.findViewById(R.id.toolbar); // Use rootView.findViewById() instead of findViewById()
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar); // Make sure to cast getActivity() to AppCompatActivity

        recyclerView = rootView.findViewById(R.id.rv); // Use rootView.findViewById() instead of findViewById()
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity())); // Use getActivity() to get the context

        FirebaseRecyclerOptions<studlist> options =
                new FirebaseRecyclerOptions.Builder<studlist>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FYP Student List"), studlist.class)
                        .build();

        //mainAdapter = new MainAdapter(options);
        recyclerView.setAdapter(mainAdapter);

        floatingActionButton = rootView.findViewById(R.id.floatingActionButton); // Use rootView.findViewById() instead of findViewById()
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddActivity.class)); // Use getActivity() to get the context
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
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

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void txtSearch(String str) {
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FYP Student List").orderByChild("StudentName").startAt(str).endAt(str + "~"), MainModel.class)
                        .build();

        mainAdapter.updateOptions(options); // Update adapter options
    }

}