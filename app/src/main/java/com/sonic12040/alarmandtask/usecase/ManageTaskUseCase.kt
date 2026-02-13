package com.sonic12040.alarmandtask.usecase

import com.sonic12040.alarmandtask.data.model.Task
import com.sonic12040.alarmandtask.data.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for managing task operations.
 * Handles task creation, updates, completion, and reminder scheduling.
 */
class ManageTaskUseCase(
    private val repository: RoutineRepository
) {
    
    suspend fun createTask(task: Task): Long {
        val taskId = repository.insertTask(task)
        // Schedule reminders for the task
        scheduleTaskReminders(task.copy(id = taskId))
        return taskId
    }
    
    suspend fun updateTask(task: Task) {
        repository.updateTask(task)
        // Reschedule reminders
        scheduleTaskReminders(task)
    }
    
    suspend fun deleteTask(taskId: Long) {
        // Cancel scheduled reminders
        cancelTaskReminders(taskId)
        repository.deleteTask(taskId)
    }
    
    suspend fun completeTask(taskId: Long) {
        repository.getTask(taskId).collect { task ->
            task?.let {
                val completedTask = it.copy(isCompleted = true)
                repository.updateTask(completedTask)
            }
        }
    }
    
    fun getTasksForRoutine(routineId: Long): Flow<List<Task>> {
        return repository.getTasksForRoutine(routineId)
    }
    
    fun getAllTasks(): Flow<List<Task>> {
        return repository.getAllTasks()
    }
    
    private fun scheduleTaskReminders(task: Task) {
        // Implementation for scheduling task reminders
        // This will use WorkManager or AlarmManager
    }
    
    private fun cancelTaskReminders(taskId: Long) {
        // Implementation for canceling task reminders
    }
}
