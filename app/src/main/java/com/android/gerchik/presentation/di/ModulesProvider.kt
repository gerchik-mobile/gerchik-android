package com.android.gerchik.presentation.di

import com.android.gerchik.presentation.di.modules.repositoryModule
import com.android.gerchik.presentation.di.modules.viewModelModule

internal fun provideModules() = arrayOf(
    repositoryModule,
    viewModelModule
)