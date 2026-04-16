package com.campusconnect.models;

public class Announcement {
    private int id;
    private String title;
    private String body;
    private String date;

    public Announcement(int id, String title, String body, String date) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getBody() { return body; }
    public String getDate() { return date; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setBody(String body) { this.body = body; }
    public void setDate(String date) { this.date = date; }
}
