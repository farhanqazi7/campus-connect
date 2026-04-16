package com.campusconnect.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campusconnect.R;
import com.campusconnect.adapters.AnnouncementAdapter;
import com.campusconnect.models.Announcement;
import com.campusconnect.network.ApiHelper;

import java.util.ArrayList;
import java.util.List;

public class AnnouncementsActivity extends AppCompatActivity {

    private static final String TAG = "AnnouncementsActivity";

    private RecyclerView rvAnnouncements;
    private ProgressBar progressBar;
    private TextView tvEmpty;
    private ImageView ivBack;
    private AnnouncementAdapter adapter;
    private List<Announcement> announcementList;
    private ApiHelper apiHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "🟢 onCreate() called");
        setContentView(R.layout.activity_announcements);

        // Initialize views
        rvAnnouncements = findViewById(R.id.rvAnnouncements);
        progressBar = findViewById(R.id.progressBar);
        tvEmpty = findViewById(R.id.tvEmpty);
        ivBack = findViewById(R.id.ivBack);

        // Setup RecyclerView
        announcementList = new ArrayList<>();
        adapter = new AnnouncementAdapter(announcementList);
        rvAnnouncements.setLayoutManager(new LinearLayoutManager(this));
        rvAnnouncements.setAdapter(adapter);

        // Back button
        ivBack.setOnClickListener(v -> finish());

        // Fetch all announcements from API (Internet Usage)
        fetchAllAnnouncements();
    }

    private void fetchAllAnnouncements() {
        progressBar.setVisibility(View.VISIBLE);
        tvEmpty.setVisibility(View.GONE);

        apiHelper = new ApiHelper(this);
        Log.d(TAG, "🌐 Fetching all announcements...");

        apiHelper.fetchAnnouncements(new ApiHelper.AnnouncementCallback() {
            @Override
            public void onSuccess(List<Announcement> announcements) {
                Log.d(TAG, "✅ Loaded " + announcements.size() + " announcements");
                progressBar.setVisibility(View.GONE);
                announcementList.clear();
                announcementList.addAll(announcements);
                adapter.notifyDataSetChanged();

                if (announcements.isEmpty()) {
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(String error) {
                Log.e(TAG, "❌ Error: " + error);
                progressBar.setVisibility(View.GONE);
                tvEmpty.setVisibility(View.VISIBLE);
                tvEmpty.setText(getString(R.string.error_network));
                Toast.makeText(AnnouncementsActivity.this, error, Toast.LENGTH_LONG).show();
            }
        });
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
