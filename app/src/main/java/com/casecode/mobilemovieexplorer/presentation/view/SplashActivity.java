package com.casecode.mobilemovieexplorer.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;

import com.casecode.mobilemovieexplorer.R;
import com.casecode.mobilemovieexplorer.databinding.ActivitySplashBinding;
import com.casecode.mobilemovieexplorer.presentation.MainActivity;

public class SplashActivity extends AppCompatActivity {

    // Set the delay time for the splash screen
    private static final long SPLASH_TIME_OUT = 2000; // 2 seconds
    private ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        // Use a Handler to delay the start of the main activity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Start the main activity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Close the splash activity so the user can't go back to it
        }, SPLASH_TIME_OUT);
    }
}