package com.campusconnect.models;

import java.io.Serializable;

public class Course implements Serializable {
    private String courseId;
    private String courseName;
    private String courseCode;
    private String instructor;
    private String schedule;
    private String room;
    private int credits;
    private String description;

    public Course(String courseId, String courseName, String courseCode,
                  String instructor, String schedule, String room,
                  int credits, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
        this.schedule = schedule;
        this.room = room;
        this.credits = credits;
        this.description = description;
    }

    // Getters
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public String getInstructor() { return instructor; }
    public String getSchedule() { return schedule; }
    public String getRoom() { return room; }
    public int getCredits() { return credits; }
    public String getDescription() { return description; }

    // Setters
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public void setSchedule(String schedule) { this.schedule = schedule; }
    public void setRoom(String room) { this.room = room; }
    public void setCredits(int credits) { this.credits = credits; }
    public void setDescription(String description) { this.description = description; }
}
