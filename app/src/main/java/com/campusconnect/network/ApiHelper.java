package com.campusconnect.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.campusconnect.models.Announcement;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ApiHelper {

    private static final String TAG = "ApiHelper";

    // Using JSONPlaceholder as a free API for announcements
    private static final String ANNOUNCEMENTS_URL =
            "https://jsonplaceholder.typicode.com/posts?_limit=10";

    private RequestQueue requestQueue;
    private Context context;

    public ApiHelper(Context context) {
        this.context = context;
        this.requestQueue = Volley.newRequestQueue(context);
    }

    // Interface for callback
    public interface AnnouncementCallback {
        void onSuccess(List<Announcement> announcements);
        void onError(String error);
    }

    // Fetch announcements from API (Internet Usage - Explicit)
    public void fetchAnnouncements(final AnnouncementCallback callback) {
        Log.d(TAG, "Fetching announcements from: " + ANNOUNCEMENTS_URL);

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                ANNOUNCEMENTS_URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "API Response received: " + response.length() + " items");
                        List<Announcement> announcements = new ArrayList<>();

                        try {
                            String[] dates = {
                                "Jan 15, 2025", "Jan 14, 2025", "Jan 13, 2025",
                                "Jan 12, 2025", "Jan 11, 2025", "Jan 10, 2025",
                                "Jan 9, 2025", "Jan 8, 2025", "Jan 7, 2025",
                                "Jan 6, 2025"
                            };

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                Announcement announcement = new Announcement(
                                        obj.getInt("id"),
                                        capitalizeTitle(obj.getString("title")),
                                        obj.getString("body"),
                                        dates[i % dates.length]
                                );
                                announcements.add(announcement);
                            }
                            callback.onSuccess(announcements);
                        } catch (Exception e) {
                            Log.e(TAG, "JSON parsing error: " + e.getMessage());
                            callback.onError("Error parsing data: " + e.getMessage());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "API Error: " + error.getMessage());
                        callback.onError("Network error: " +
                                (error.getMessage() != null ? error.getMessage() : "Unknown error"));
                    }
                }
        );

        requestQueue.add(request);
    }

    private String capitalizeTitle(String title) {
        if (title == null || title.isEmpty()) return title;
        String[] words = title.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                sb.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    sb.append(word.substring(1));
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}
