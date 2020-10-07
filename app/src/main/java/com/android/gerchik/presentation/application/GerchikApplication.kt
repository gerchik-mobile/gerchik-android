package com.android.gerchik.presentation.application

import android.app.Application
import com.android.gerchik.presentation.di.provideModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

internal class GerchikApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initServiceLocator()
    }

    private fun initServiceLocator() {
        startKoin {
            androidLogger(level = Level.DEBUG)
            androidContext(this@GerchikApplication)
            modules(*provideModules())
        }
    }
}