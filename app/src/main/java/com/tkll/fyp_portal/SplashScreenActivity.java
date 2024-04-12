package com.tkll.fyp_portal;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    // Variable declarations
    private static int SPLASH_SCREEN_TOTAL_DURATION = 9000;
    ProgressBar progressBar;
    LottieAnimationView lottieAnimationView;
    TextView appName_FYP, appName_Portal;
    ConstraintLayout appName_Group;
    Guideline guideln1, guideln2;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        // Hooks
        lottieAnimationView = findViewById(R.id.lottie);
        appName_FYP = findViewById(R.id.appname_fyp);
        appName_Portal = findViewById(R.id.appname_portal);
        progressBar = findViewById(R.id.progressbar);
        guideln1 = findViewById(R.id.splashscr_horizontaltop002);
        guideln2 = findViewById(R.id.splashscr_progressbarbottom080);
        appName_Group = findViewById(R.id.appname_group);

        // Delayed animation sequence
        new Handler().postDelayed(() -> {
            // Move TextViews upwards
            animateConstraintLayoutUpwardsByGuideline(appName_Group, guideln1);
            animateProgressBarDownwardsByGuideline(progressBar, guideln2);

            // Show and animate LottieAnimationView
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();

            // Delayed hide LottieAnimationView and move Views downwards
            new Handler().postDelayed(() -> {
                lottieAnimationView.setVisibility(View.INVISIBLE);
                animateConstraintLayoutDownwards(appName_Group);
                animateProgressBarUpwards(progressBar);
            }, 4000); // Wait for 4 seconds (duration of LottieAnimationView)

            // Start progress animation
            progress();
        }, 2000); // Delay for 2 seconds before starting animations

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, Login.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN_TOTAL_DURATION);
    }

    public void progress(){
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                progressBar.setProgress(counter);

                if (counter == 100) t.cancel();
            }
        };

        t.schedule(tt, 0, 70);
    }

    private void animateConstraintLayoutUpwardsByGuideline(ConstraintLayout textGroup, Guideline guideline) {
        float targetY = guideline.getY(); // Get the Y position of the guideline
        float initialY = textGroup.getY(); // Get the initial Y position of the ConstraintLayout
        float distanceToMove = initialY - targetY; // Calculate the distance to move

        // Create ObjectAnimator for the ConstraintLayout
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                textGroup,
                "translationY",
                -distanceToMove
        );
        animator.setDuration(1000); // Adjust the duration as needed
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        // Start the animation
        animator.start();
    }

    private void animateProgressBarDownwardsByGuideline(ProgressBar progressBar, Guideline guideline) {
        float targetY = guideline.getY(); // Get the Y position of the guideline
        float initialY = progressBar.getY(); // Get the initial Y position of the ProgressBar
        float distanceToMove = targetY - initialY; // Calculate the distance to move

        // Create ObjectAnimator for the ProgressBar
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                progressBar,
                "translationY",
                distanceToMove
        );
        animator.setDuration(1000); // Adjust the duration as needed
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        // Start the animation
        animator.start();
    }

    private void animateConstraintLayoutDownwards(ConstraintLayout textGroup) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                textGroup,
                "translationY",
                0 // Move ConstraintLayout downwards to its original position
        );
        animator.setDuration(1000); // Adjust the duration as needed
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }

    private void animateProgressBarUpwards(ProgressBar progressBar) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                progressBar,
                "translationY",
                0 // Move ProgressBar upwards to its original position
        );
        animator.setDuration(1000); // Adjust the duration as needed
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}