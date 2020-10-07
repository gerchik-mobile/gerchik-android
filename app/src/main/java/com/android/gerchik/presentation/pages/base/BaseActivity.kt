package com.android.gerchik.presentation.pages.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.android.gerchik.presentation.extensions.hideSoftKeyboard
import com.android.gerchik.presentation.extensions.showMessage
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel> : AppCompatActivity() {

    protected abstract val layoutRes: Int
    protected abstract val viewModelClass: KClass<VM>

    protected lateinit var binding: V
    protected val viewModel by lazy { getViewModel(viewModelClass) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutRes)
        binding.apply {
            lifecycleOwner = this@BaseActivity
        }

        viewModel.apply {
            onShowMessage.observe(this@BaseActivity) { showMessage(it) }
            onShowError.observe(this@BaseActivity) { showMessage(it) }
            onCloseKeyboard.observe(this@BaseActivity) { hideSoftKeyboard() }
            onClosePage.observe(this@BaseActivity) { finish() }
            observeViewModel()
        }
    }

    protected open fun VM.observeViewModel() {
        /* Default implementation */
    }
}