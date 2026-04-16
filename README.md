```md
# Campus Connect

Campus Connect is an Android-based student utility application designed to help university students manage daily academic activities efficiently. The app provides features such as login, dashboard access, course details, announcements from an online API, and student profile management in a simple and user-friendly interface.

## Features

- Splash / Welcome screen
- Student login system
- Dashboard with student information
- Course list using RecyclerView
- Course details screen
- Announcements fetched from an online API
- Student profile screen
- Data passing between activities
- Activity lifecycle logging
- Toast messages for user feedback
- SQLite-based student authentication

## App Flow

1. Splash Screen
2. Login Screen
3. Dashboard
4. Course Details / Announcements / Profile
5. Logout back to Login

## Demo Login Credentials

- **Email:** `student@campus.edu`
- **Password:** `pass123`

## Technologies Used

### Programming Language
- Java

### UI Design
- XML

### IDE / Editor
- Visual Studio Code (VS Code)

### Build Tools
- Gradle 8.4
- Android Gradle Plugin 8.1.4

### Android SDK
- Compile SDK: 34
- Target SDK: 34
- Minimum SDK: 24

### Libraries and Dependencies
- AndroidX AppCompat
- ConstraintLayout
- RecyclerView
- CardView
- Material Components
- Volley
- Gson

### Database
- SQLite using `SQLiteOpenHelper`

### Networking
- REST API
- JSONPlaceholder API for announcements

### Platform
- Android
- Ubuntu 24 development environment

## Project Structure

```text
CampusConnect/
├── app/
│   ├── build.gradle
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/campusconnect/
│       │   ├── activities/
│       │   ├── adapters/
│       │   ├── models/
│       │   ├── database/
│       │   └── network/
│       └── res/
│           ├── layout/
│           ├── drawable/
│           └── values/
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

## Main Activities

- `SplashActivity`
- `LoginActivity`
- `DashboardActivity`
- `CourseDetailActivity`
- `AnnouncementsActivity`
- `ProfileActivity`

## Core Concepts Implemented

### Multiple Activities
The application contains more than three activities to demonstrate Android navigation and screen transitions.

### Internet Usage
Announcements are fetched from an online API using Volley.

### Data Passing Between Activities
- Login → Dashboard: student name, email, ID, department, semester
- Dashboard → Course Detail: course name, code, instructor, credits, room, schedule, description
- Dashboard → Profile: student information

### Activity Lifecycle
Lifecycle methods used:
- `onCreate()`
- `onStart()`
- `onResume()`
- `onPause()`
- `onStop()`
- `onDestroy()`

Lifecycle events are displayed using Logcat.

### UI Widgets Used
- EditText
- Button
- TextView
- ImageView
- RecyclerView
- CardView
- ProgressBar
- ScrollView

## API Used

The app uses the following API for announcements:

- `https://jsonplaceholder.typicode.com/posts?_limit=10`

This is used to simulate university announcements.

## Database

SQLite is used to store and verify student login credentials.

Default student record:
- Student ID: `STU-2024-001`
- Name: `Alex Johnson`
- Email: `student@campus.edu`
- Password: `pass123`
- Department: `Computer Science`
- Semester: `6th Semester`

## Build and Run

### Prerequisites
- Java 17
- Android SDK
- Gradle 8.4
- ADB
- Ubuntu 24 or compatible Linux system

### Build APK
```bash
./gradlew clean assembleDebug
```

### APK Output
```bash
app/build/outputs/apk/debug/app-debug.apk
```

### Install on Device
```bash
adb install app/build/outputs/apk/debug/app-debug.apk
```

### Launch App
```bash
adb shell am start -n com.campusconnect/.activities.SplashActivity
```

## Logcat Command

To view lifecycle and app logs:

```bash
adb logcat -s SplashActivity LoginActivity DashboardActivity CourseDetailActivity AnnouncementsActivity ProfileActivity ApiHelper DatabaseHelper
```

## Expected Outcome

Campus Connect demonstrates the understanding of Android fundamentals including:
- Activity management
- Layout design
- User interaction
- Intent data passing
- Lifecycle handling
- RecyclerView usage
- Networking with API
- SQLite database integration

## Author

Developed as an Android student utility application project.

## License

This project is for educational purposes.
```
