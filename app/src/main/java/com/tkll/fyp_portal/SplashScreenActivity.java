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
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity {
    // Variable declarations
    private static int SPLASH_SCREEN_TOTAL_DURATION = 10000;
    ProgressBar progressBar;
    LottieAnimationView lottieAnimationView;
    TextView appName_FYP, appName_Portal;
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

        // Delayed animation sequence
        new Handler().postDelayed(() -> {
            // Move TextViews upwards
            animateTextViewUpwards(appName_FYP, 730);
            animateTextViewUpwards(appName_Portal, 730);

            // Show and animate LottieAnimationView
            lottieAnimationView.setVisibility(View.VISIBLE);
            lottieAnimationView.playAnimation();

            // Delayed hide LottieAnimationView and move TextViews downwards
            new Handler().postDelayed(() -> {
                lottieAnimationView.setVisibility(View.INVISIBLE);
                animateTextViewDownwards(appName_FYP);
                animateTextViewDownwards(appName_Portal);
            }, 4000); // Wait for 4 seconds (duration of LottieAnimationView)

            // Start progress animation
            progress();
        }, 2000); // Delay for 2 seconds before starting animations

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
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

        t.schedule(tt, 0, 80);
    }

    private void animateTextViewUpwards(TextView textView, int distance_to_move) {
        // Create ObjectAnimator for the TextView
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                textView,
                "translationY",
                -distance_to_move
        );
        animator.setDuration(1000); // Adjust the duration as needed
        animator.setInterpolator(new AccelerateDecelerateInterpolator());

        // Start the animation
        animator.start();
    }

    private void animateTextViewDownwards(TextView textView) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(
                textView,
                "translationY",
                0 // Move TextView downwards to its original position
        );
        animator.setDuration(1000); // Adjust the duration as needed
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}