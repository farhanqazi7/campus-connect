package com.campusconnect.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.campusconnect.models.Student;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "campus_connect.db";
    private static final int DATABASE_VERSION = 1;

    // Student Table
    private static final String TABLE_STUDENTS = "students";
    private static final String COL_ID = "id";
    private static final String COL_STUDENT_ID = "student_id";
    private static final String COL_NAME = "name";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_DEPARTMENT = "department";
    private static final String COL_SEMESTER = "semester";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_STUDENTS + " ("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_STUDENT_ID + " TEXT, "
                + COL_NAME + " TEXT, "
                + COL_EMAIL + " TEXT UNIQUE, "
                + COL_PASSWORD + " TEXT, "
                + COL_DEPARTMENT + " TEXT, "
                + COL_SEMESTER + " TEXT)";
        db.execSQL(createTable);
        Log.d(TAG, "Database table created");

        // Insert default student
        ContentValues values = new ContentValues();
        values.put(COL_STUDENT_ID, "STU-2024-001");
        values.put(COL_NAME, "Alex Johnson");
        values.put(COL_EMAIL, "student@campus.edu");
        values.put(COL_PASSWORD, "pass123");
        values.put(COL_DEPARTMENT, "Computer Science");
        values.put(COL_SEMESTER, "6th Semester");
        db.insert(TABLE_STUDENTS, null, values);
        Log.d(TAG, "Default student inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    // Authenticate student
    public Student authenticateStudent(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENTS,
                null,
                COL_EMAIL + "=? AND " + COL_PASSWORD + "=?",
                new String[]{email, password},
                null, null, null);

        Student student = null;
        if (cursor.moveToFirst()) {
            student = new Student(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_STUDENT_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_DEPARTMENT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_SEMESTER))
            );
        }
        cursor.close();
        return student;
    }

    // Get student by email
    public Student getStudentByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENTS,
                null,
                COL_EMAIL + "=?",
                new String[]{email},
                null, null, null);

        Student student = null;
        if (cursor.moveToFirst()) {
            student = new Student(
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_STUDENT_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_DEPARTMENT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COL_SEMESTER))
            );
        }
        cursor.close();
        return student;
    }
}
