package com.android.gerchik.presentation.extensions

import android.content.Context
import android.text.Spannable
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import com.android.gerchik.presentation.common.StringResource

fun EditText.showKeyboard() {
    requestFocus()
    setSelection(text.length)
    isCursorVisible = true
    val imm = this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun TextView.setText(stringResource: StringResource?) {
    text = stringResource?.let { (id, args) -> context.getString(id, *args) } ?: ""
}

/**
 * Spannable.
 **/
fun Spannable.setColorOf(allString: String, coloredString: String, color: Int) {
    val colorSpan = ForegroundColorSpan(color)
    val startIndex = allString.indexOf(coloredString)
    val endIndex = startIndex + coloredString.length
    setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}

fun Spannable.setListenerOf(allString: String, coloredString: String, listener: () -> Unit) {
    val clickableSpan = object : ClickableSpan() {
        override fun onClick(widget: View) = listener()
    }
    val startIndex = allString.indexOf(coloredString)
    val endIndex = startIndex + coloredString.length
    setSpan(clickableSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
}