package com.tkll.fyp_portal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tkll.fyp_portal.MainAdapterOfferedTitles;
import com.tkll.fyp_portal.MainModel;
import com.tkll.fyp_portal.R;

import java.util.ArrayList;

public class OfferedTitles_IIPSPW extends Fragment {

    private RecyclerView recyclerView;
    private DatabaseReference database;
    private MainAdapterOfferedTitles mainAdapterOfferedTitles;
    private ArrayList<MainModel> list;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Offered Titles");
        mainAdapterOfferedTitles = new MainAdapterOfferedTitles(getActivity(), list);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear(); // Clear the list before adding new data
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MainModel mainModel = dataSnapshot.getValue(MainModel.class);
                    list.add(mainModel);
                }
                mainAdapterOfferedTitles.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_offeredtitles_iipspw, container, false);

        recyclerView = rootView.findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(mainAdapterOfferedTitles);

        return rootView;
    }
}
