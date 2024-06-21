package com.scotiabank.assignment.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * BaseApplication class required by Hilt for dependency injection.
 * This class is annotated with @HiltAndroidApp to trigger Hilt's code generation,
 * including a base class for the application that serves as the application-level dependency container.
 */
@HiltAndroidApp
class BaseApplication : Application()
