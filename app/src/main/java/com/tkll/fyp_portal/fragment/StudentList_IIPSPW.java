package com.tkll.fyp_portal.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tkll.fyp_portal.MainAdapterIIPSPWStudentList;
import com.tkll.fyp_portal.MainModel3;
import com.tkll.fyp_portal.R;

import java.util.ArrayList;

public class StudentList_IIPSPW extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference database;
    MainAdapterIIPSPWStudentList mainAdapterIIPSPWStudentList;
    ArrayList<MainModel3> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_studentlist_iipspw, container, false);

        recyclerView = rootView.findViewById(R.id.rv);
        database = FirebaseDatabase.getInstance().getReference("IIPSPW Student List");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        mainAdapterIIPSPWStudentList = new MainAdapterIIPSPWStudentList(getActivity(), list);
        recyclerView.setAdapter(mainAdapterIIPSPWStudentList);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    MainModel3 mainModel3 = dataSnapshot.getValue(MainModel3.class);
                    list.add(mainModel3);
                }
                mainAdapterIIPSPWStudentList.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rootView;
    }
}
