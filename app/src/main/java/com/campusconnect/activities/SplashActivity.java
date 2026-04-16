package com.campusconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.campusconnect.R;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private static final int SPLASH_DURATION = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "🟢 onCreate() called");
        setContentView(R.layout.activity_splash);

        // Navigate to Login after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Log.d(TAG, "Navigating to LoginActivity");
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DURATION);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "🟡 onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "🟢 onResume() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "🟠 onPause() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "🔴 onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "⚫ onDestroy() called");
    }
}
