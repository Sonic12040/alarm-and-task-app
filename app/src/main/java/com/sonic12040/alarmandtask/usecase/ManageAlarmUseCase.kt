package com.sonic12040.alarmandtask.usecase

import com.sonic12040.alarmandtask.data.model.Alarm
import com.sonic12040.alarmandtask.data.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for managing alarm operations.
 * Handles alarm scheduling, updates, and cancellation.
 */
class ManageAlarmUseCase(
    private val repository: RoutineRepository
) {
    
    suspend fun createAlarm(alarm: Alarm): Long {
        // Business logic for alarm validation
        val alarmId = repository.insertAlarm(alarm)
        // Schedule alarm with Android AlarmManager
        scheduleAlarm(alarm.copy(id = alarmId))
        return alarmId
    }
    
    suspend fun updateAlarm(alarm: Alarm) {
        repository.updateAlarm(alarm)
        // Reschedule alarm
        scheduleAlarm(alarm)
    }
    
    suspend fun deleteAlarm(alarmId: Long) {
        // Cancel scheduled alarm
        cancelAlarm(alarmId)
        repository.deleteAlarm(alarmId)
    }
    
    fun getAlarmsForRoutine(routineId: Long): Flow<List<Alarm>> {
        return repository.getAlarmsForRoutine(routineId)
    }
    
    fun getAllAlarms(): Flow<List<Alarm>> {
        return repository.getAllAlarms()
    }
    
    private fun scheduleAlarm(alarm: Alarm) {
        // Implementation for scheduling alarm with AlarmManager
        // This will be implemented with actual Android AlarmManager integration
    }
    
    private fun cancelAlarm(alarmId: Long) {
        // Implementation for canceling alarm
    }
}
