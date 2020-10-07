package com.android.gerchik.presentation.pages.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.gerchik.presentation.common.ActionLiveData
import com.android.gerchik.presentation.common.StringResource

abstract class BaseViewModel : ViewModel() {

    val isProgressVisible = MutableLiveData<Boolean>()
    val onShowMessage = MutableLiveData<StringResource>()
    val onShowError = MutableLiveData<StringResource>()
    val onClosePage = ActionLiveData<Unit>()
    val onCloseKeyboard = ActionLiveData<Unit>()
}