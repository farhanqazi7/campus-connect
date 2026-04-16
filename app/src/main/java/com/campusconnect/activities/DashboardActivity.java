package com.campusconnect.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campusconnect.R;
import com.campusconnect.adapters.AnnouncementAdapter;
import com.campusconnect.adapters.CourseAdapter;
import com.campusconnect.models.Announcement;
import com.campusconnect.models.Course;
import com.campusconnect.network.ApiHelper;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity
        implements CourseAdapter.OnCourseClickListener {

    private static final String TAG = "DashboardActivity";

    // Student data received from Login
    private String studentName, studentEmail, studentId, studentDept, studentSemester;

    // UI Components
    private TextView tvStudentName, tvCourseCount, tvAnnouncementCount;
    private ImageView ivProfile;
    private RecyclerView rvCourses, rvAnnouncements;
    private Button btnLogout;
    private TextView tvViewAllAnnouncements;

    // Data
    private List<Course> courseList;
    private List<Announcement> announcementList;
    private CourseAdapter courseAdapter;
    private AnnouncementAdapter announcementAdapter;
    private ApiHelper apiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "🟢 onCreate() called");
        setContentView(R.layout.activity_dashboard);

        // Receive data from Login Activity (Data Passing)
        receiveIntentData();

        // Initialize views
        initViews();

        // Setup courses
        setupCourses();

        // Fetch announcements from Internet (API)
        fetchAnnouncementsFromApi();

        // Setup click listeners
        setupClickListeners();
    }

    private void receiveIntentData() {
        Intent intent = getIntent();
        studentName = intent.getStringExtra("STUDENT_NAME");
        studentEmail = intent.getStringExtra("STUDENT_EMAIL");
        studentId = intent.getStringExtra("STUDENT_ID");
        studentDept = intent.getStringExtra("STUDENT_DEPARTMENT");
        studentSemester = intent.getStringExtra("STUDENT_SEMESTER");

        Log.d(TAG, "📨 Received data - Name: " + studentName + ", Email: " + studentEmail);
    }

    private void initViews() {
        tvStudentName = findViewById(R.id.tvStudentName);
        tvCourseCount = findViewById(R.id.tvCourseCount);
        tvAnnouncementCount = findViewById(R.id.tvAnnouncementCount);
        ivProfile = findViewById(R.id.ivProfile);
        rvCourses = findViewById(R.id.rvCourses);
        rvAnnouncements = findViewById(R.id.rvAnnouncements);
        btnLogout = findViewById(R.id.btnLogout);
        tvViewAllAnnouncements = findViewById(R.id.tvViewAllAnnouncements);

        // Set student name
        tvStudentName.setText(studentName != null ? studentName : "Student");
    }

    private void setupCourses() {
        courseList = getSampleCourses();
        tvCourseCount.setText(String.valueOf(courseList.size()));

        courseAdapter = new CourseAdapter(courseList, this);
        rvCourses.setLayoutManager(new LinearLayoutManager(this));
        rvCourses.setAdapter(courseAdapter);
    }

    private void fetchAnnouncementsFromApi() {
        apiHelper = new ApiHelper(this);
        announcementList = new ArrayList<>();
        announcementAdapter = new AnnouncementAdapter(announcementList);
        rvAnnouncements.setLayoutManager(new LinearLayoutManager(this));
        rvAnnouncements.setAdapter(announcementAdapter);

        // Fetch from Internet - EXPLICIT INTERNET USAGE
        Log.d(TAG, "🌐 Fetching announcements from Internet...");
        apiHelper.fetchAnnouncements(new ApiHelper.AnnouncementCallback() {
            @Override
            public void onSuccess(List<Announcement> announcements) {
                Log.d(TAG, "✅ Received " + announcements.size() + " announcements from API");

                // Show only first 3 on dashboard
                List<Announcement> dashboardAnnouncements =
                        announcements.subList(0, Math.min(3, announcements.size()));

                announcementList.clear();
                announcementList.addAll(dashboardAnnouncements);
                announcementAdapter.notifyDataSetChanged();

                tvAnnouncementCount.setText(String.valueOf(announcements.size()));

                Toast.makeText(DashboardActivity.this,
                        "Loaded " + announcements.size() + " announcements",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "❌ API Error: " + error);
                Toast.makeText(DashboardActivity.this,
                        getString(R.string.error_network),
                        Toast.LENGTH_LONG).show();

                // Load fallback data
                loadFallbackAnnouncements();
            }
        });
    }

    private void loadFallbackAnnouncements() {
        announcementList.clear();
        announcementList.add(new Announcement(1,
                "Mid-term Exam Schedule Released",
                "Check the exam schedule on the university portal.",
                "Jan 15, 2025"));
        announcementList.add(new Announcement(2,
                "Library Hours Extended",
                "Library will remain open until 10 PM during exam week.",
                "Jan 14, 2025"));
        announcementList.add(new Announcement(3,
                "Campus WiFi Maintenance",
                "WiFi will be unavailable from 2-4 AM on Saturday.",
                "Jan 13, 2025"));
        announcementAdapter.notifyDataSetChanged();
        tvAnnouncementCount.setText(String.valueOf(announcementList.size()));
    }

    private void setupClickListeners() {
        // Profile click
        ivProfile.setOnClickListener(v -> {
            Log.d(TAG, "Profile icon clicked");
            Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
            intent.putExtra("STUDENT_NAME", studentName);
            intent.putExtra("STUDENT_EMAIL", studentEmail);
            intent.putExtra("STUDENT_ID", studentId);
            intent.putExtra("STUDENT_DEPARTMENT", studentDept);
            intent.putExtra("STUDENT_SEMESTER", studentSemester);
            startActivity(intent);
        });

        // View all announcements
        tvViewAllAnnouncements.setOnClickListener(v -> {
            Log.d(TAG, "View all announcements clicked");
            Intent intent = new Intent(DashboardActivity.this, AnnouncementsActivity.class);
            startActivity(intent);
        });

        // Logout
        btnLogout.setOnClickListener(v -> {
            Log.d(TAG, "Logout clicked");
            Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }

    // Course click - Pass data to CourseDetailActivity
    @Override
    public void onCourseClick(Course course) {
        Log.d(TAG, "📚 Course clicked: " + course.getCourseName());
        Toast.makeText(this, "Opening: " + course.getCourseName(), Toast.LENGTH_SHORT).show();

        // Pass course data to Course Detail Activity (Data Passing)
        Intent intent = new Intent(DashboardActivity.this, CourseDetailActivity.class);
        intent.putExtra("COURSE_NAME", course.getCourseName());
        intent.putExtra("COURSE_CODE", course.getCourseCode());
        intent.putExtra("COURSE_INSTRUCTOR", course.getInstructor());
        intent.putExtra("COURSE_SCHEDULE", course.getSchedule());
        intent.putExtra("COURSE_ROOM", course.getRoom());
        intent.putExtra("COURSE_CREDITS", course.getCredits());
        intent.putExtra("COURSE_DESCRIPTION", course.getDescription());
        startActivity(intent);
    }

    private List<Course> getSampleCourses() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("1", "Data Structures & Algorithms", "CS301",
                "Dr. Sarah Johnson", "Mon/Wed 10:00 AM", "Room 301", 3,
                "Study of fundamental data structures including arrays, linked lists, trees, graphs, and their associated algorithms. Covers sorting, searching, and complexity analysis."));
        courses.add(new Course("2", "Database Management Systems", "CS302",
                "Prof. Michael Chen", "Tue/Thu 11:30 AM", "Room 205", 3,
                "Introduction to database concepts, ER modeling, relational algebra, SQL, normalization, and transaction management."));
        courses.add(new Course("3", "Operating Systems", "CS303",
                "Dr. Emily Williams", "Mon/Wed 2:00 PM", "Room 401", 4,
                "Study of OS concepts including process management, memory management, file systems, and concurrency control."));
        courses.add(new Course("4", "Computer Networks", "CS304",
                "Prof. David Brown", "Tue/Thu 9:00 AM", "Room 102", 3,
                "Fundamentals of computer networking, TCP/IP protocol suite, routing, switching, and network security."));
        courses.add(new Course("5", "Software Engineering", "CS305",
                "Dr. Lisa Anderson", "Fri 10:00 AM", "Room 503", 3,
                "Software development lifecycle, agile methodologies, design patterns, testing, and project management."));
        courses.add(new Course("6", "Mobile App Development", "CS306",
                "Prof. James Wilson", "Mon/Wed 4:00 PM", "Lab 201", 3,
                "Android application development using Java, covering UI design, activities, data storage, and API integration."));
        return courses;
    }

    // Activity Lifecycle Methods with Log messages
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "🟡 onStart() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "🟢 onResume() called");
        Toast.makeText(this, "Dashboard Active", Toast.LENGTH_SHORT).show();
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
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "🔄 onRestart() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "⚫ onDestroy() called");
    }
}
