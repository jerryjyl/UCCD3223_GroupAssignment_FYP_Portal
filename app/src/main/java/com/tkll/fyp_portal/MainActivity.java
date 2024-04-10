package com.tkll.fyp_portal;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView introTextView = findViewById(R.id.textViewIntro);
        TextView introCollapseButton = findViewById(R.id.introCollapseBtn);
        WebView announcementContentWv= findViewById(R.id.webViewAnnouncementsContents);
        ImageView announcementDropdownTg = findViewById(R.id.announcementDropdownToggle);

        announcementContentWv.loadUrl("file:///android_asset/announcements.html");

        introCollapseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (introTextView.getVisibility() == View.GONE) {
                    introTextView.setVisibility(View.VISIBLE);
                    introCollapseButton.setText("Collapse");
                } else {
                    introTextView.setVisibility(View.GONE);
                    introCollapseButton.setText("Expand");
                }
            }
        });

        announcementDropdownTg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (announcementContentWv.getVisibility() == View.GONE) {
                    announcementContentWv.setVisibility(View.VISIBLE);
                    announcementDropdownTg.setImageResource(R.drawable.ic_arrow_drop_down);
                } else {
                    announcementContentWv.setVisibility(View.GONE);
                    announcementDropdownTg.setImageResource(R.drawable.ic_arrow_drop_up);
                }
            }
        });









    }
}