package com.android.gerchik.presentation.di.modules

import com.android.gerchik.presentation.pages.greeting.GreetingActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val viewModelModule = module {
    viewModel { GreetingActivityViewModel() }
}