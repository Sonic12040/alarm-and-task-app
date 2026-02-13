package com.sonic12040.alarmandtask.data.repository

import com.sonic12040.alarmandtask.data.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing routines and their associated items.
 * Implementations can use Room database, remote data sources, or other storage mechanisms.
 */
interface RoutineRepository {
    
    // Routine operations
    suspend fun insertRoutine(routine: Routine): Long
    suspend fun updateRoutine(routine: Routine)
    suspend fun deleteRoutine(routineId: Long)
    fun getRoutine(routineId: Long): Flow<Routine?>
    fun getAllRoutines(): Flow<List<Routine>>
    
    // Alarm operations
    suspend fun insertAlarm(alarm: Alarm): Long
    suspend fun updateAlarm(alarm: Alarm)
    suspend fun deleteAlarm(alarmId: Long)
    fun getAlarm(alarmId: Long): Flow<Alarm?>
    fun getAlarmsForRoutine(routineId: Long): Flow<List<Alarm>>
    fun getAllAlarms(): Flow<List<Alarm>>
    
    // Task operations
    suspend fun insertTask(task: Task): Long
    suspend fun updateTask(task: Task)
    suspend fun deleteTask(taskId: Long)
    fun getTask(taskId: Long): Flow<Task?>
    fun getTasksForRoutine(routineId: Long): Flow<List<Task>>
    fun getAllTasks(): Flow<List<Task>>
    
    // Task Notification operations
    suspend fun insertTaskNotification(notification: TaskNotification): Long
    suspend fun updateTaskNotification(notification: TaskNotification)
    suspend fun deleteTaskNotification(notificationId: Long)
    fun getTaskNotification(notificationId: Long): Flow<TaskNotification?>
    fun getTaskNotificationsForRoutine(routineId: Long): Flow<List<TaskNotification>>
    fun getAllTaskNotifications(): Flow<List<TaskNotification>>
    
    // Label operations
    suspend fun insertLabel(label: Label): Long
    suspend fun updateLabel(label: Label)
    suspend fun deleteLabel(labelId: Long)
    fun getLabel(labelId: Long): Flow<Label?>
    fun getAllLabels(): Flow<List<Label>>
}
