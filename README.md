# Alarm and Task App

An Android application for managing alarms and tasks through configurable routines.

## Features

### Routines
- Create and manage routines that can contain multiple alarms, tasks, and notifications
- Label routines for better organization
- Enable/disable entire routines at once

### Alarms
- Configurable alarms with custom times
- Support for system and custom ringtones
- Vibration and snooze settings
- Repeat alarms on specific days of the week
- Assignable labels

### Tasks
- Create tasks with priorities (Low, Medium, High, Urgent)
- Set due dates and recurrence patterns (Daily, Weekly, Monthly, Yearly, Custom)
- Multiple reminders per task with notification timeframes
- Track task completion status
- Assignable labels

### Task Notifications
- Standalone notifications independent of tasks
- Custom ringtones and notification priorities
- Scheduled notification delivery
- Assignable labels

### Labels
- Create custom labels with colors
- Assign labels to routines, alarms, tasks, and notifications
- Better organization and categorization

## Project Structure

```
app/src/main/java/com/sonic12040/alarmandtask/
├── data/
│   ├── model/           # Data models for Routine, Alarm, Task, etc.
│   └── repository/      # Repository interfaces for data access
├── ui/                  # UI components (Activities, Fragments)
├── viewmodel/           # ViewModels for UI state management
└── usecase/             # Business logic use cases

app/src/main/res/
├── layout/              # XML layout files
├── values/              # Strings, colors, themes
└── mipmap-*/           # App launcher icons
```

## Architecture

This app follows Clean Architecture principles with:
- **Data Layer**: Models and repository interfaces
- **Domain Layer**: Use cases for business logic
- **Presentation Layer**: ViewModels and UI components

## Technology Stack

- **Language**: Kotlin
- **UI**: Material Design 3 components
- **Architecture**: MVVM with Clean Architecture
- **Async**: Kotlin Coroutines and Flow
- **Dependency Injection**: Manual (can be upgraded to Hilt/Dagger)
- **Database**: Room (interface ready, implementation needed)
- **Scheduling**: WorkManager for task reminders

## Building the Project

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device

### Requirements
- Android Studio Arctic Fox or later
- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Kotlin 1.9.20+

## Next Steps

This scaffolding provides the foundation. Future development includes:
- Implementing Room database DAOs and entities
- Adding alarm scheduling with AlarmManager
- Implementing task reminder scheduling with WorkManager
- Creating UI for routine, alarm, task, and notification management
- Adding settings and preferences
- Implementing notification channels
- Adding data export/import functionality

## License

See LICENSE file for details.
