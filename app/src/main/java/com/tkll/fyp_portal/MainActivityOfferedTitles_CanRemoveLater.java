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

public class MainActivityOfferedTitles_CanRemoveLater extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    MainAdapterOfferedTitles mainAdapterOfferedTitles;
    ArrayList<MainModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_offeredtitles_iipspw);

        recyclerView= findViewById(R.id.rv);
        database=FirebaseDatabase.getInstance().getReference("Offered Titles");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        mainAdapterOfferedTitles=new MainAdapterOfferedTitles(this,list);
        recyclerView.setAdapter(mainAdapterOfferedTitles);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    MainModel mainModel=dataSnapshot.getValue(MainModel.class);
                    list.add(mainModel);
                }
                mainAdapterOfferedTitles.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

