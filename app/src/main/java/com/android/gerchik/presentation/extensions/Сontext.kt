package com.android.gerchik.presentation.extensions

import android.content.Context
import android.content.res.Resources
import com.android.gerchik.presentation.common.StringResource

fun Context.getString(stringResource: StringResource?): String {
    return stringResource?.let { (id, args) ->
        getString(id, *args)
    } ?: ""
}

fun Context.toPx(size: Int): Int = (size * Resources.getSystem().displayMetrics.density).toInt()

fun Context.toPx(size: Float): Float = size * Resources.getSystem().displayMetrics.density