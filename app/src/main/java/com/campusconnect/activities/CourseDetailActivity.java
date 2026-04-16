package com.campusconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.campusconnect.R;

public class CourseDetailActivity extends AppCompatActivity {

    private static final String TAG = "CourseDetailActivity";

    private TextView tvCourseName, tvCourseCode, tvCourseCredits;
    private TextView tvInstructor, tvSchedule, tvRoom, tvDescription;
    private ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "🟢 onCreate() called");
        setContentView(R.layout.activity_course_detail);

        // Initialize views
        initViews();

        // Receive course data passed from Dashboard (Data Passing)
        receiveIntentData();

        // Back button
        ivBack.setOnClickListener(v -> {
            Log.d(TAG, "Back button pressed");
            finish();
        });
    }

    private void initViews() {
        tvCourseName = findViewById(R.id.tvCourseName);
        tvCourseCode = findViewById(R.id.tvCourseCode);
        tvCourseCredits = findViewById(R.id.tvCourseCredits);
        tvInstructor = findViewById(R.id.tvInstructor);
        tvSchedule = findViewById(R.id.tvSchedule);
        tvRoom = findViewById(R.id.tvRoom);
        tvDescription = findViewById(R.id.tvDescription);
        ivBack = findViewById(R.id.ivBack);
    }

    private void receiveIntentData() {
        Intent intent = getIntent();

        String courseName = intent.getStringExtra("COURSE_NAME");
        String courseCode = intent.getStringExtra("COURSE_CODE");
        String instructor = intent.getStringExtra("COURSE_INSTRUCTOR");
        String schedule = intent.getStringExtra("COURSE_SCHEDULE");
        String room = intent.getStringExtra("COURSE_ROOM");
        int credits = intent.getIntExtra("COURSE_CREDITS", 0);
        String description = intent.getStringExtra("COURSE_DESCRIPTION");

        Log.d(TAG, "📨 Received course data - Name: " + courseName + ", Code: " + courseCode);

        // Set data to views
        tvCourseName.setText(courseName);
        tvCourseCode.setText(courseCode);
        tvCourseCredits.setText(credits + " Credits");
        tvInstructor.setText(instructor);
        tvSchedule.setText(schedule);
        tvRoom.setText(room);
        tvDescription.setText(description);
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
