package com.campusconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.campusconnect.R;
import com.campusconnect.database.DatabaseHelper;
import com.campusconnect.models.Student;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "🟢 onCreate() called");
        setContentView(R.layout.activity_login);

        // Initialize database
        dbHelper = new DatabaseHelper(this);

        // Initialize UI components (5+ widgets used)
        etEmail = findViewById(R.id.etEmail);       // Widget 1: EditText
        etPassword = findViewById(R.id.etPassword); // Widget 2: EditText
        btnLogin = findViewById(R.id.btnLogin);     // Widget 3: Button
        // Widget 4: ImageView (in layout - logo)
        // Widget 5: TextView (in layout - titles, labels)

        // Set click listener
        btnLogin.setOnClickListener(v -> attemptLogin());
    }

    private void attemptLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        Log.d(TAG, "Login attempt with email: " + email);

        // Validate input
        if (email.isEmpty()) {
            etEmail.setError("Email is required");
            etEmail.requestFocus();
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            etPassword.setError("Password is required");
            etPassword.requestFocus();
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Authenticate using database
        Student student = dbHelper.authenticateStudent(email, password);

        if (student != null) {
            // Login success - Toast message
            Log.d(TAG, "✅ Login successful for: " + student.getName());
            Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();

            // Pass data to Dashboard Activity (Data Passing between Activities)
            Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
            intent.putExtra("STUDENT_NAME", student.getName());
            intent.putExtra("STUDENT_EMAIL", student.getEmail());
            intent.putExtra("STUDENT_ID", student.getStudentId());
            intent.putExtra("STUDENT_DEPARTMENT", student.getDepartment());
            intent.putExtra("STUDENT_SEMESTER", student.getSemester());
            startActivity(intent);
            finish();
        } else {
            // Login failed - Toast message
            Log.d(TAG, "❌ Login failed for email: " + email);
            Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_LONG).show();
        }
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
