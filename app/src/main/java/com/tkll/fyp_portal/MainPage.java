package com.tkll.fyp_portal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class MainPage extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        TextView introTextView = rootView.findViewById(R.id.textViewIntro);
        TextView introCollapseButton = rootView.findViewById(R.id.introCollapseBtn);
        WebView announcementContentWv = rootView.findViewById(R.id.webViewAnnouncementsContents);
        ImageView announcementDropdownTg = rootView.findViewById(R.id.announcementDropdownToggle);

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

        return rootView;
    }
}