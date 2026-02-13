# Architecture Overview

This document describes the architecture and structure of the Alarm and Task App.

## Architecture Pattern

The app follows **Clean Architecture** principles with **MVVM (Model-View-ViewModel)** pattern:

```
┌─────────────────────────────────────────────────────────────┐
│                     Presentation Layer                      │
│  ┌──────────────┐     ┌──────────────┐    ┌─────────────┐ │
│  │   Activity   │────▶│  ViewModel   │────│  LiveData/  │ │
│  │  Fragment    │     │              │    │    Flow     │ │
│  └──────────────┘     └──────────────┘    └─────────────┘ │
│         │                     │                             │
│         └─────────────────────┼─────────────────────────────┘
│                               │
│                               ▼
│  ┌─────────────────────────────────────────────────────────┐
│  │                    Domain Layer                         │
│  │  ┌──────────────┐        ┌──────────────┐              │
│  │  │   Use Case   │        │  Repository  │              │
│  │  │              │◀───────│  Interface   │              │
│  │  └──────────────┘        └──────────────┘              │
│  └─────────────────────────────────────────────────────────┘
│                               │
│                               ▼
│  ┌─────────────────────────────────────────────────────────┐
│  │                     Data Layer                          │
│  │  ┌──────────────┐        ┌──────────────┐              │
│  │  │  Data Model  │        │ Repository   │              │
│  │  │              │        │ Implementation│              │
│  │  └──────────────┘        └──────────────┘              │
│  │         │                        │                      │
│  │         ▼                        ▼                      │
│  │  ┌──────────────┐        ┌──────────────┐              │
│  │  │ Room Entity  │        │  Room DAO    │              │
│  │  └──────────────┘        └──────────────┘              │
│  └─────────────────────────────────────────────────────────┘
```

## Layer Details

### 1. Presentation Layer (`ui/` and `viewmodel/`)

**Responsibilities:**
- Display data to the user
- Handle user interactions
- Observe data changes

**Components:**
- `MainActivity`: Entry point of the application
- `ViewModel`: Manages UI-related data, survives configuration changes
- ViewBinding: Type-safe view access

**Key Files:**
- `ui/MainActivity.kt`: Main screen
- `viewmodel/RoutineViewModel.kt`: State management for routines

### 2. Domain Layer (`usecase/`)

**Responsibilities:**
- Business logic
- Coordinate data flow between UI and data layers
- Encapsulate complex operations

**Components:**
- `ManageRoutineUseCase`: Operations for routines
- `ManageAlarmUseCase`: Alarm scheduling and management
- `ManageTaskUseCase`: Task operations and reminder scheduling

**Purpose:**
- Keep business logic separate from UI and data access
- Make code testable and reusable
- Single Responsibility Principle

### 3. Data Layer (`data/`)

**Responsibilities:**
- Data persistence
- Data access abstraction
- Data models

**Components:**

#### Models (`data/model/`)
- `Routine`: Container for alarms, tasks, notifications
- `Alarm`: Alarm configuration with ringtone support
- `Task`: Task with priority, recurrence, reminders
- `TaskNotification`: Standalone notifications
- `Label`: Categorization labels

#### Repository (`data/repository/`)
- `RoutineRepository` (interface): Defines data operations
- Implementation will use Room database

## Data Models Details

### Routine
- **Purpose**: Group related alarms, tasks, and notifications
- **Key Properties**: name, description, enabled status, label
- **Relationships**: Has many alarms, tasks, notifications

### Alarm
- **Purpose**: Time-based alarms with configurable settings
- **Key Properties**: 
  - Time: hour, minute
  - Days of week for recurring alarms
  - Ringtone: type (system/custom), URI
  - Settings: vibrate, snooze
- **Features**: 
  - System ringtone selection
  - Custom ringtone support
  - Snooze configuration

### Task
- **Purpose**: Trackable items with due dates and priorities
- **Key Properties**:
  - Priority: LOW, MEDIUM, HIGH, URGENT
  - Recurrence: Daily, Weekly, Monthly, Yearly, Custom
  - Reminders: Multiple with notification timeframes
- **Features**:
  - Completion tracking
  - Flexible recurrence patterns
  - Multiple reminders per task

### TaskNotification
- **Purpose**: Standalone scheduled notifications
- **Key Properties**:
  - Scheduled time
  - Ringtone configuration
  - Priority level
- **Features**:
  - Independent from tasks
  - Custom ringtone support
  - Configurable priority

### Label
- **Purpose**: Organize and categorize items
- **Key Properties**: name, color, icon
- **Usage**: Can be assigned to routines, alarms, tasks, notifications

## Technology Stack

### Core
- **Language**: Kotlin 1.9.20
- **Min SDK**: 24 (Android 7.0)
- **Target SDK**: 34 (Android 14)

### UI
- **Material Design 3**: Modern UI components
- **ViewBinding**: Type-safe view access
- **ConstraintLayout**: Flexible layouts
- **CoordinatorLayout**: Advanced UI behavior

### Architecture
- **Lifecycle Components**: ViewModel, LiveData
- **Coroutines**: Asynchronous programming
- **Flow**: Reactive data streams

### Data (To be implemented)
- **Room**: Local database
- **DataStore**: Preferences (future)

### Background Work
- **WorkManager**: Scheduled tasks and reminders
- **AlarmManager**: Exact alarm scheduling

## Design Patterns

### Repository Pattern
- Abstracts data sources
- Single source of truth
- Easy to test and mock

### Observer Pattern
- Flow for data streams
- LiveData for UI updates
- Reactive programming

### Dependency Injection (Manual)
- Constructor injection
- Can be upgraded to Hilt/Dagger

## Future Enhancements

### Database Implementation
- Create Room entities from data models
- Implement DAOs (Data Access Objects)
- Implement RoutineRepository
- Add database migrations

### Alarm System
- AlarmManager integration
- BroadcastReceiver for alarm triggers
- Full-screen alarm activity
- Notification channels

### Task System
- WorkManager for task reminders
- Recurring task handling
- Task completion history
- Task statistics

### UI Enhancements
- RecyclerView for item lists
- Detail screens for editing
- Bottom sheets for quick actions
- Search and filtering
- Swipe actions

### Settings
- User preferences
- Theme selection (Light/Dark/Auto)
- Notification settings
- Backup and restore

### Testing
- Unit tests for ViewModels and UseCases
- Integration tests for Repository
- UI tests with Espresso
- Test coverage reporting

## Code Organization

```
app/src/main/java/com/sonic12040/alarmandtask/
├── data/
│   ├── model/
│   │   ├── Alarm.kt
│   │   ├── Label.kt
│   │   ├── Routine.kt
│   │   ├── Task.kt
│   │   └── TaskNotification.kt
│   └── repository/
│       └── RoutineRepository.kt (interface)
├── ui/
│   └── MainActivity.kt
├── usecase/
│   ├── ManageAlarmUseCase.kt
│   ├── ManageRoutineUseCase.kt
│   └── ManageTaskUseCase.kt
└── viewmodel/
    └── RoutineViewModel.kt
```

## Build Configuration

### Project-level (`build.gradle.kts`)
- Android Gradle Plugin: 8.1.4
- Kotlin: 1.9.20

### Module-level (`app/build.gradle.kts`)
- Compile SDK: 34
- Min SDK: 24
- Target SDK: 34
- Dependencies:
  - AndroidX Core, AppCompat, Material
  - Lifecycle components
  - Room database
  - Coroutines
  - WorkManager

## Best Practices Implemented

1. **Separation of Concerns**: Each layer has clear responsibilities
2. **Single Responsibility**: Each class has one primary purpose
3. **Dependency Inversion**: Dependencies point inward (UI → Domain → Data)
4. **Testability**: Business logic isolated from framework
5. **Immutability**: Data classes with immutable properties
6. **Null Safety**: Kotlin's null safety features utilized
7. **Coroutines**: Proper async/await patterns
8. **Flow**: Reactive data streams for database queries

## Security Considerations

### Permissions
- `SCHEDULE_EXACT_ALARM`: For precise alarm timing
- `POST_NOTIFICATIONS`: For showing notifications (Android 13+)
- `VIBRATE`: For alarm vibration
- `WAKE_LOCK`: To wake device for alarms
- `RECEIVE_BOOT_COMPLETED`: To reschedule alarms after reboot

### Data Security
- Local storage only (Room database)
- No sensitive data transmitted over network
- Proper permission handling

## Performance Considerations

1. **Database Queries**: Use Flow for reactive updates
2. **Background Work**: Use WorkManager and AlarmManager appropriately
3. **Memory Management**: Proper lifecycle handling with ViewModels
4. **UI Rendering**: Efficient RecyclerView with ViewHolder pattern
5. **Threading**: Coroutines for background operations

## Accessibility

- Proper content descriptions
- Material Design components (built-in accessibility)
- Keyboard navigation support
- Screen reader compatibility

## Internationalization

- Externalized strings in `strings.xml`
- Ready for multi-language support
- RTL layout support enabled

## License

See LICENSE file for details.
