package com.sonic12040.alarmandtask.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sonic12040.alarmandtask.data.model.*
import com.sonic12040.alarmandtask.data.repository.RoutineRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for managing routines and their associated items.
 * Handles UI state and business logic for routine management.
 */
class RoutineViewModel(
    private val repository: RoutineRepository
) : ViewModel() {

    private val _routines = MutableStateFlow<List<Routine>>(emptyList())
    val routines: StateFlow<List<Routine>> = _routines

    private val _selectedRoutine = MutableStateFlow<Routine?>(null)
    val selectedRoutine: StateFlow<Routine?> = _selectedRoutine

    init {
        loadRoutines()
    }

    private fun loadRoutines() {
        viewModelScope.launch {
            repository.getAllRoutines().collect { routineList ->
                _routines.value = routineList
            }
        }
    }

    fun createRoutine(name: String, description: String, label: Label? = null) {
        viewModelScope.launch {
            val routine = Routine(
                name = name,
                description = description,
                label = label
            )
            repository.insertRoutine(routine)
        }
    }

    fun updateRoutine(routine: Routine) {
        viewModelScope.launch {
            repository.updateRoutine(routine)
        }
    }

    fun deleteRoutine(routineId: Long) {
        viewModelScope.launch {
            repository.deleteRoutine(routineId)
        }
    }

    fun selectRoutine(routineId: Long) {
        viewModelScope.launch {
            repository.getRoutine(routineId).collect { routine ->
                _selectedRoutine.value = routine
            }
        }
    }

    // Alarm operations
    fun addAlarmToRoutine(routineId: Long, alarm: Alarm) {
        viewModelScope.launch {
            repository.insertAlarm(alarm.copy(routineId = routineId))
        }
    }

    fun updateAlarm(alarm: Alarm) {
        viewModelScope.launch {
            repository.updateAlarm(alarm)
        }
    }

    fun deleteAlarm(alarmId: Long) {
        viewModelScope.launch {
            repository.deleteAlarm(alarmId)
        }
    }

    // Task operations
    fun addTaskToRoutine(routineId: Long, task: Task) {
        viewModelScope.launch {
            repository.insertTask(task.copy(routineId = routineId))
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            repository.updateTask(task)
        }
    }

    fun deleteTask(taskId: Long) {
        viewModelScope.launch {
            repository.deleteTask(taskId)
        }
    }

    // Task Notification operations
    fun addNotificationToRoutine(routineId: Long, notification: TaskNotification) {
        viewModelScope.launch {
            repository.insertTaskNotification(notification.copy(routineId = routineId))
        }
    }

    fun updateNotification(notification: TaskNotification) {
        viewModelScope.launch {
            repository.updateTaskNotification(notification)
        }
    }

    fun deleteNotification(notificationId: Long) {
        viewModelScope.launch {
            repository.deleteTaskNotification(notificationId)
        }
    }
}
