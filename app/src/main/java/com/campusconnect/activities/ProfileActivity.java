package com.campusconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.campusconnect.R;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "🟢 onCreate() called");
        setContentView(R.layout.activity_profile);

        // Initialize views
        TextView tvName = findViewById(R.id.tvProfileName);
        TextView tvEmail = findViewById(R.id.tvProfileEmail);
        TextView tvStudentId = findViewById(R.id.tvStudentId);
        TextView tvDepartment = findViewById(R.id.tvDepartment);
        TextView tvSemester = findViewById(R.id.tvSemester);
        ImageView ivBack = findViewById(R.id.ivBack);

        // Receive data passed from Dashboard
        Intent intent = getIntent();
        String name = intent.getStringExtra("STUDENT_NAME");
        String email = intent.getStringExtra("STUDENT_EMAIL");
        String studentId = intent.getStringExtra("STUDENT_ID");
        String department = intent.getStringExtra("STUDENT_DEPARTMENT");
        String semester = intent.getStringExtra("STUDENT_SEMESTER");

        Log.d(TAG, "📨 Received profile data - Name: " + name);

        // Set data
        tvName.setText(name != null ? name : "Student");
        tvEmail.setText(email != null ? email : "N/A");
        tvStudentId.setText(studentId != null ? studentId : "N/A");
        tvDepartment.setText(department != null ? department : "N/A");
        tvSemester.setText(semester != null ? semester : "N/A");

        // Back button
        ivBack.setOnClickListener(v -> finish());
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
