package com.android.gerchik.presentation.pages.greeting

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import com.android.gerchik.R
import com.android.gerchik.databinding.ActivityGreetingBinding
import com.android.gerchik.presentation.extensions.setColorOf
import com.android.gerchik.presentation.extensions.setListenerOf
import com.android.gerchik.presentation.extensions.showMessage
import com.android.gerchik.presentation.pages.base.BaseActivity
import com.android.gerchik.presentation.pages.register.RegisterActivity

class GreetingActivity : BaseActivity<ActivityGreetingBinding, GreetingActivityViewModel>() {

    override val layoutRes = R.layout.activity_greeting
    override val viewModelClass = GreetingActivityViewModel::class

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        initUI()
    }

    private fun initUI() = with(binding) {
        initConditionOfUse()
    }

    override fun GreetingActivityViewModel.observeViewModel() {
        onOpenRegisterPage.observe(this@GreetingActivity) {
            startActivity(RegisterActivity.getIntent(this@GreetingActivity))
            finish()
        }
        onOpenLoginPage.observe(this@GreetingActivity) {
            showMessage("todo: onOpenLoginPage")
        }
    }

    private fun initConditionOfUse() = with(binding.conditionOfUse) {
        val nonClickablePart = getString(R.string.greeting_register_and_accept)
        val privacyPolitic = getString(R.string.greeting_privacy_politic)
        val and = getString(R.string.greeting_and)
        val serviceConditions = getString(R.string.greeting_service_condition)
        val merged = StringBuilder()
            .append("$nonClickablePart ")
            .append("$privacyPolitic ")
            .append("$and ")
            .append(serviceConditions).toString()

        val spannable = SpannableString(merged).apply {
            setListenerOf(merged, privacyPolitic, ::onPrivacyPoliticClick)
            setListenerOf(merged, serviceConditions, ::onServiceConditionsClick)

            setColorOf(merged, privacyPolitic, getColor(R.color.lightBlueTextColor))
            setColorOf(merged, serviceConditions, getColor(R.color.lightBlueTextColor))
        }

        movementMethod = LinkMovementMethod.getInstance()
        text = spannable
    }

    private fun onPrivacyPoliticClick() {
        showMessage("todo: onPrivacyPoliticClick")
    }

    private fun onServiceConditionsClick() {
        showMessage("todo: onServiceConditionsClick")
    }
}