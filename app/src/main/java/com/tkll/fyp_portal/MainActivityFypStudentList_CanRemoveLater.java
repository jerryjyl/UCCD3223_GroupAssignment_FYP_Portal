package com.tkll.fyp_portal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivityFypStudentList_CanRemoveLater extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MainAdapterFypStudentList mainAdapterFypStudentList;
    ArrayList<MainModel3> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_studentlist_fyp);

        recyclerView= findViewById(R.id.rv);
        database=FirebaseDatabase.getInstance().getReference("FYP Student List");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        mainAdapterFypStudentList=new MainAdapterFypStudentList(this,list);
        recyclerView.setAdapter(mainAdapterFypStudentList);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MainModel3 mainModel3=dataSnapshot.getValue(MainModel3.class);
                    list.add(mainModel3);
                }
                mainAdapterFypStudentList.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

