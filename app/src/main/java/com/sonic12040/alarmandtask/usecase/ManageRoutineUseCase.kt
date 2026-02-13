package com.sonic12040.alarmandtask.usecase

import com.sonic12040.alarmandtask.data.model.Routine
import com.sonic12040.alarmandtask.data.repository.RoutineRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case for managing routine operations.
 * Encapsulates business logic for routine creation, update, and deletion.
 */
class ManageRoutineUseCase(
    private val repository: RoutineRepository
) {
    
    suspend fun createRoutine(routine: Routine): Long {
        // Business logic validation can be added here
        return repository.insertRoutine(routine)
    }
    
    suspend fun updateRoutine(routine: Routine) {
        repository.updateRoutine(routine)
    }
    
    suspend fun deleteRoutine(routineId: Long) {
        // Additional logic like deleting associated alarms, tasks, etc.
        repository.deleteRoutine(routineId)
    }
    
    fun getRoutine(routineId: Long): Flow<Routine?> {
        return repository.getRoutine(routineId)
    }
    
    fun getAllRoutines(): Flow<List<Routine>> {
        return repository.getAllRoutines()
    }
}
