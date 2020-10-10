package com.android.gerchik.presentation.di.modules

import com.android.gerchik.presentation.pages.greeting.GreetingActivityViewModel
import com.android.gerchik.presentation.pages.register.RegisterActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { GreetingActivityViewModel() }
    viewModel { RegisterActivityViewModel() }
}