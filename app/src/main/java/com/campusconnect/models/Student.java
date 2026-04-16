package com.campusconnect.models;

public class Student {
    private String studentId;
    private String name;
    private String email;
    private String department;
    private String semester;

    public Student(String studentId, String name, String email,
                   String department, String semester) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.department = department;
        this.semester = semester;
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }
    public String getSemester() { return semester; }

    // Setters
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDepartment(String department) { this.department = department; }
    public void setSemester(String semester) { this.semester = semester; }
}
