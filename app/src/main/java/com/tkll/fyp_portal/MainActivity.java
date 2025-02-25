package com.tkll.fyp_portal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.tkll.fyp_portal.fragment.AmendmentForm_FYP;
import com.tkll.fyp_portal.fragment.AmendmentForm_IIPSPW;
import com.tkll.fyp_portal.fragment.FYP_UndertakingForm_IIPSPW;
import com.tkll.fyp_portal.fragment.OfferedTitles_IIPSPW;
import com.tkll.fyp_portal.fragment.StudentList_FYP;
import com.tkll.fyp_portal.fragment.StudentList_IIPSPW;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView nvgView = findViewById(R.id.navigation_view);
        nvgView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerTg = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_navigation, R.string.close_navigation);

        drawerLayout.addDrawerListener(drawerTg);
        drawerTg.syncState();

        if (savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainPage()).commit();
            nvgView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Intent intent;

        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainPage()).commit();
                break;

            case R.id.compose_email:
                SendMailActivity sendMailActivity = new SendMailActivity();
                sendMailActivity.show(getSupportFragmentManager(), "SendMailBottomSheet");
                break;

            case R.id.iipspw_offeredtitles:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new OfferedTitles_IIPSPW()).commit();
                break;

            case R.id.iipspw_undertakingform:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FYP_UndertakingForm_IIPSPW()).commit();
                break;

            case R.id.iipspw_stdlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StudentList_IIPSPW()).commit();
                break;

            case R.id.iipspw_amendmentform:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AmendmentForm_IIPSPW()).commit();
                break;

            case R.id.fyp_stdlist:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new StudentList_FYP()).commit();
                break;

            case R.id.fyp_amendmentform:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AmendmentForm_FYP()).commit();
                break;

            case R.id.fyp_reportsubmission:
//                intent = new Intent(MainActivity.this, ReportSubmission_Fyp.class);
//                startActivity(intent);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ReportSubmission_Fyp()).commit();
                break;

            case R.id.nav_logout:
                logout();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else super.onBackPressed();
    }

    private void logout() {
        // Add code here to perform logout actions if any
        // For example, clearing user session, resetting preferences, etc.

        // Redirect to the Login activity
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(), "Logged out successfully", Toast.LENGTH_SHORT).show();
        finish(); // Optional: Close the current activity to prevent going back to it using the back button
    }

}