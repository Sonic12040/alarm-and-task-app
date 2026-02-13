package com.sonic12040.alarmandtask.data.model

/**
 * Represents an Alarm within a routine.
 * Supports configurable ringtones from system or custom sources.
 */
data class Alarm(
    val id: Long = 0,
    val routineId: Long,
    val name: String,
    val hour: Int,
    val minute: Int,
    val isEnabled: Boolean = true,
    val daysOfWeek: List<Int> = emptyList(), // 1=Monday, 7=Sunday
    val ringtoneType: RingtoneType = RingtoneType.SYSTEM_DEFAULT,
    val ringtoneUri: String? = null, // URI for custom ringtone or system ringtone selection
    val vibrate: Boolean = true,
    val snoozeEnabled: Boolean = true,
    val snoozeDurationMinutes: Int = 10,
    val label: Label? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

/**
 * Type of ringtone for the alarm
 */
enum class RingtoneType {
    SYSTEM_DEFAULT,
    SYSTEM_SELECTION,
    CUSTOM
}
