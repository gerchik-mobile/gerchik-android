package com.android.gerchik.presentation.pages.greeting

import com.android.gerchik.presentation.common.ActionLiveData
import com.android.gerchik.presentation.pages.base.BaseViewModel

class GreetingActivityViewModel : BaseViewModel() {

    val onOpenRegisterPage = ActionLiveData<Unit>()
    val onOpenLoginPage = ActionLiveData<Unit>()
    
    fun onRegisterClick() {
        onOpenRegisterPage.call()
    }

    fun onLoginClick() {
        onOpenLoginPage.call()
    }
}