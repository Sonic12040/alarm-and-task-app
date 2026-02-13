package com.sonic12040.alarmandtask.data.model

/**
 * Label that can be assigned to routines, alarms, tasks, and notifications
 * for better organization and categorization.
 */
data class Label(
    val id: Long = 0,
    val name: String,
    val color: Int, // Color in Android Color format (ARGB)
    val icon: String? = null, // Optional icon identifier
    val createdAt: Long = System.currentTimeMillis()
)
