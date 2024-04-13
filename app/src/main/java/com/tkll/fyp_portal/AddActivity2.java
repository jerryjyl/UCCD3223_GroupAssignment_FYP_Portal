package com.tkll.fyp_portal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity2 extends AppCompatActivity {
    EditText studentname,supervisorname,programme,
            projecttitle,areaofstudy,projecttype, moderatorname;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        studentname=(EditText)findViewById(R.id.txtStudentName);
        projecttitle=(EditText)findViewById(R.id.txtProjectTitle);
        supervisorname=(EditText)findViewById(R.id.txtSupervisorName);
        programme=(EditText)findViewById(R.id.txtProgramme);
        areaofstudy=(EditText)findViewById(R.id.txtAreaOfStudy);
        projecttype=(EditText)findViewById(R.id.txtProjectType);
        moderatorname=(EditText)findViewById(R.id.txtModeratorName);


        btnAdd=(Button) findViewById(R.id.btnAdd);
        btnBack=(Button) findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDate();
                clearAll();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void insertDate(){
        Map<String,Object> map=new HashMap<>();
        map.put("StudentName",studentname.getText().toString());
        map.put("ProjectTitle",projecttitle.getText().toString());
        map.put("SupervisorName",supervisorname.getText().toString());
        map.put("ProjectType",projecttype.getText().toString());
        map.put("AreaOfStudy",areaofstudy.getText().toString());
        map.put("Programme",programme.getText().toString());
        map.put("ModeratorName",moderatorname.getText().toString());


        FirebaseDatabase.getInstance().getReference().child("IIPSPW Student List").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity2.this,"Data Inserted Successfully.",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity2.this,"Error While Insertion.",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll(){
        studentname.setText("");
        projecttitle.setText("");
        supervisorname.setText("");
        programme.setText("");
        areaofstudy.setText("");
        projecttype.setText("");
        moderatorname.setText("");

    }
}
