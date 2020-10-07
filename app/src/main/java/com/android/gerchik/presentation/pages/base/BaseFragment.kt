package com.android.gerchik.presentation.pages.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.android.gerchik.presentation.extensions.hideSoftKeyboard
import com.android.gerchik.presentation.extensions.showMessage
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<V : ViewDataBinding, VM : BaseViewModel> : Fragment() {

    private val baseActivity get() = (activity as? BaseActivity<*, *>)

    protected abstract val layoutRes: Int
    protected abstract val viewModelClass: KClass<VM>

    protected lateinit var binding: V
    protected val viewModel by lazy { getViewModel(viewModelClass) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = with(DataBindingUtil.inflate<V>(layoutInflater, layoutRes, container, false)) {
        binding = this
        lifecycleOwner = this@BaseFragment.viewLifecycleOwner
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.apply {
            onShowMessage.observe(viewLifecycleOwner) { showMessage(it) }
            onShowError.observe(viewLifecycleOwner) { showMessage(it) }
            onCloseKeyboard.observe(viewLifecycleOwner) { baseActivity?.hideSoftKeyboard() }
            observeViewModel()
        }
    }

    protected open fun VM.observeViewModel() {
        /* Default implementation */
    }
}