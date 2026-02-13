package com.sonic12040.alarmandtask.data.model

/**
 * Represents a Routine that can contain multiple alarms, tasks, and notifications.
 * A routine is a collection of related items that can be managed together.
 */
data class Routine(
    val id: Long = 0,
    val name: String,
    val description: String = "",
    val isEnabled: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val label: Label? = null
)
