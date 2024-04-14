package com.tkll.fyp_portal.fragment;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import com.tkll.fyp_portal.R;

public class StudentList_FYP extends Fragment {
    RecyclerView recyclerView;
    listadapter mainAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_studentlist_fyp, container, false);

        // Setup Toolbar
        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        recyclerView = rootView.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        FirebaseRecyclerOptions<listmodel> options =
                new FirebaseRecyclerOptions.Builder<listmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("FYP Student List"), listmodel.class)
                        .build();

        mainAdapter = new listadapter(options);
        recyclerView.setAdapter(mainAdapter);

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

}