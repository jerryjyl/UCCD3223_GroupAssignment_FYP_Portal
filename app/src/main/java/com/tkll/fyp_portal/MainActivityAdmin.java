package com.tkll.fyp_portal;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivityAdmin extends AppCompatActivity {

    private Button offeredTitleButton;
    private Button iipspwStudentListButton;
    private Button fypStudentListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_admin);

        offeredTitleButton = findViewById(R.id.offeredTitleButton);
        iipspwStudentListButton = findViewById(R.id.iipspwStudentListButton);
        fypStudentListButton = findViewById(R.id.fypStudentListButton);

        offeredTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAdmin.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        iipspwStudentListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAdmin.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        fypStudentListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityAdmin.this, MainActivity4.class);
                startActivity(intent);
            }
        });
    }
}
