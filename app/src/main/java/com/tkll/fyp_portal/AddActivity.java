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

public class AddActivity extends AppCompatActivity {
    EditText supervisor,projecttitle,description,expectedoutcome,areaofstudy,skillsrequired, nostudent,equipment, suitablefor;
    Button btnAdd,btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        supervisor=(EditText)findViewById(R.id.txtSupervisorName);
        projecttitle=(EditText)findViewById(R.id.txtProjectTitle);
        description=(EditText)findViewById(R.id.txtDescription);
        expectedoutcome=(EditText)findViewById(R.id.txtExpectedOutcome);
        areaofstudy=(EditText)findViewById(R.id.txtAreaOfStudy);
        skillsrequired=(EditText)findViewById(R.id.txtSkillsRequired);
        nostudent=(EditText)findViewById(R.id.txtNoStudents);
        equipment=(EditText)findViewById(R.id.txtEquipment);
        suitablefor=(EditText)findViewById(R.id.txtSuitableFor);

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
        map.put("Supervisor",supervisor.getText().toString());
        map.put("ProjectTitle",projecttitle.getText().toString());
        map.put("Description",description.getText().toString());
        map.put("ExpectedOutcome",expectedoutcome.getText().toString());
        map.put("AreaOfStudy",areaofstudy.getText().toString());
        map.put("SkillsRequired",skillsrequired.getText().toString());
        map.put("NoStudents",nostudent.getText().toString());
        map.put("Equipment",equipment.getText().toString());
        map.put("SuitableFor",suitablefor.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Offered Titles").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data Inserted Successfully.",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity.this,"Error While Insertion.",Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void clearAll(){
        supervisor.setText("");
        projecttitle.setText("");
        description.setText("");
        expectedoutcome.setText("");
        areaofstudy.setText("");
        skillsrequired.setText("");
        nostudent.setText("");
        equipment.setText("");
        suitablefor.setText("");

    }
}
