package com.scotiabank.assignment.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.scotiabank.assignment.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * The project's main activity that hosts RepoListFragment and RepoDetailFragment.
 * This activity serves as the entry point of the application and is responsible
 * for setting the content view to the main layout.
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
