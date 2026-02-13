package com.sonic12040.alarmandtask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sonic12040.alarmandtask.R

/**
 * Main entry point of the application.
 * Displays the list of routines and allows navigation to detailed views.
 */
class MainActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Initialize UI components
        setupUI()
    }
    
    private fun setupUI() {
        // Setup will include:
        // - RecyclerView for displaying routines
        // - FAB for creating new routines
        // - Navigation to detail screens
        // Implementation will be added based on UI requirements
    }
}
