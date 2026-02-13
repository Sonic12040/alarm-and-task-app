package com.sonic12040.alarmandtask.data.model

/**
 * Represents a Task within a routine.
 * Includes priority levels, recurrence patterns, and reminder notifications.
 */
data class Task(
    val id: Long = 0,
    val routineId: Long,
    val title: String,
    val description: String = "",
    val priority: TaskPriority = TaskPriority.MEDIUM,
    val isCompleted: Boolean = false,
    val dueDate: Long? = null,
    val recurrence: RecurrencePattern? = null,
    val reminders: List<TaskReminder> = emptyList(),
    val label: Label? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

/**
 * Priority levels for tasks
 */
enum class TaskPriority {
    LOW,
    MEDIUM,
    HIGH,
    URGENT
}

/**
 * Defines how a task recurs
 */
data class RecurrencePattern(
    val type: RecurrenceType,
    val interval: Int = 1, // e.g., every 2 days, every 3 weeks
    val daysOfWeek: List<Int> = emptyList(), // For weekly recurrence: 1=Monday, 7=Sunday
    val endDate: Long? = null
)

enum class RecurrenceType {
    DAILY,
    WEEKLY,
    MONTHLY,
    YEARLY,
    CUSTOM
}

/**
 * Reminder for a task with notification settings
 */
data class TaskReminder(
    val id: Long = 0,
    val taskId: Long,
    val timeBeforeDue: Long, // milliseconds before due date
    val notificationTitle: String = "",
    val notificationMessage: String = "",
    val ringtoneUri: String? = null,
    val isEnabled: Boolean = true
)
