package com.android.gerchik.presentation.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.android.gerchik.presentation.common.StringResource

fun View.hideSoftKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(windowToken, 0)
}

fun View.isKeyboardOpened(): Boolean {
    return rootView?.let {
        val rect = Rect()
        it.getWindowVisibleDisplayFrame(rect)
        val screenHeight = rootView.rootView.height
        val keypadHeight = screenHeight - rect.bottom

        keypadHeight > screenHeight * 0.15
    } ?: false
}

fun View.goneUnless(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun View.invisibleUnless(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

@SuppressLint("UseCompatLoadingForDrawables")
fun View.getDrawable(@DrawableRes id: Int, default: ColorDrawable = ColorDrawable(Color.TRANSPARENT)): Drawable {
    return context.getDrawable(id) ?: default
}

fun View.getString(@StringRes resId: Int, vararg formatArgs: Any) = context.getString(resId, *formatArgs)

fun View.getString(stringRes: StringResource?) = context.getString(stringRes)

fun View.getStrings(stringsRes: List<StringResource>?) = stringsRes?.map { context.getString(it) } ?: emptyList()

fun View.getColor(@ColorRes resId: Int) = context.getColor(resId)

fun View.getColorStateList(@ColorRes resId: Int) = context.resources.getColorStateList(resId, context.theme)

fun View.toDp(size: Int): Int = (size / Resources.getSystem().displayMetrics.density).toInt()

fun View.toPx(size: Int): Int = context.toPx(size)

fun View.toPx(size: Float): Float = context.toPx(size)

fun View.spToPx(size: Int): Int = (size * Resources.getSystem().displayMetrics.scaledDensity).toInt()