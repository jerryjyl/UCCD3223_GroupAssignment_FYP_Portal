package com.tkll.fyp_portal;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainPage extends Fragment {
    private TextView usernameTextView;
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_page, container, false);

        usernameTextView = rootView.findViewById(R.id.textView2);

        // Initialize Firebase components
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
            Query query = usersRef.orderByChild("Email").equalTo(userEmail);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String username = userSnapshot.child("Username").getValue(String.class);
                        if (username != null) {
                            usernameTextView.setText(username);
                            break; // Assuming there's only one user with the same email
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Handle errors
                }
            });
        }

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