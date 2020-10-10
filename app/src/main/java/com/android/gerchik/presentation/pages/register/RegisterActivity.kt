package com.android.gerchik.presentation.pages.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import com.android.gerchik.R
import com.android.gerchik.databinding.ActivityRegisterBinding
import com.android.gerchik.presentation.extensions.setColorOf
import com.android.gerchik.presentation.extensions.setListenerOf
import com.android.gerchik.presentation.extensions.showMessage
import com.android.gerchik.presentation.pages.base.BaseActivity
import java.lang.StringBuilder

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterActivityViewModel>() {

    override val layoutRes = R.layout.activity_register
    override val viewModelClass = RegisterActivityViewModel::class

    companion object {
        fun getIntent(context: Context) = Intent(context, RegisterActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initHaveAnAccount()
    }

    private fun initHaveAnAccount() = with(binding.haveAnAccount) {
        val nonClickablePart = getString(R.string.register_already_have_an_account)
        val clickableLogin = getString(R.string.register_login)
        val merged = StringBuilder()
            .append("$nonClickablePart ")
            .append(clickableLogin)
            .toString()

        val spannable = SpannableString(merged).apply {
            setListenerOf(merged, clickableLogin, ::onLoginClick)
            setColorOf(merged, clickableLogin, getColor(R.color.lightBlueTextColor))
        }

        movementMethod = LinkMovementMethod.getInstance()
        text = spannable
    }

    private fun onLoginClick() {
        showMessage("todo: onLoginClick")
    }
}