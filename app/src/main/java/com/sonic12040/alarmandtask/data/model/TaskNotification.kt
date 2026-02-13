package com.sonic12040.alarmandtask.data.model

/**
 * Represents a standalone Task Notification within a routine.
 * These are independent notifications that are not tied to a specific task.
 */
data class TaskNotification(
    val id: Long = 0,
    val routineId: Long,
    val title: String,
    val message: String,
    val scheduledTime: Long, // timestamp when notification should fire
    val isEnabled: Boolean = true,
    val ringtoneType: RingtoneType = RingtoneType.SYSTEM_DEFAULT,
    val ringtoneUri: String? = null,
    val vibrate: Boolean = true,
    val priority: NotificationPriority = NotificationPriority.DEFAULT,
    val label: Label? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

/**
 * Priority levels for notifications
 */
enum class NotificationPriority {
    LOW,
    DEFAULT,
    HIGH,
    MAX
}
