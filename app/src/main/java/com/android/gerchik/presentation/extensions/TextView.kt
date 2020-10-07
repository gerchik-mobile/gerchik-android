package com.android.gerchik.presentation.extensions

import android.content.Context
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