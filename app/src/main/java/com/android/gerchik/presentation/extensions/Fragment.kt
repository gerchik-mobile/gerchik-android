package com.android.gerchik.presentation.extensions

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.android.gerchik.presentation.common.StringResource

fun Fragment.setToolbarTitle(title: String) {
    (activity as? AppCompatActivity)?.supportActionBar?.title = title
}

fun Fragment.setToolbarTitle(titleRes: StringResource?) {
    val title = titleRes?.let { getString(it.messageResId, *it.args) } ?: ""
    setToolbarTitle(title)
}

fun Fragment.hideSoftKeyboard() {
    requireActivity().hideSoftKeyboard()
}

fun Fragment.getColor(@ColorRes resId: Int) = requireActivity().getColor(resId)

fun Fragment.post(r: () -> Unit) {
    activity?.post(r)
}

fun Fragment.postDelayed(delay: Long, r: () -> Unit) {
    activity?.postDelayed(delay, r)
}

/**
 * Message extensions.
 * TODO change to application style implementation
 */
fun Fragment.showMessage(errorMessageRes: StringResource) = activity?.showMessage(errorMessageRes)

fun Fragment.showMessage(errorMessage: String) = activity?.showMessage(errorMessage)

/**
 * For fragment argument handling.
 */
fun Fragment.intExtraOrException(extraTag: String) : Int {
    return takeIf { arguments?.containsKey(extraTag) == true }?.let {
        arguments?.getInt(extraTag, 0)
    } ?: throw IllegalArgumentException("Some int extra arg is missed.")
}

fun Fragment.intExtraOrNull(extraTag: String) : Int? {
    return if (arguments?.containsKey(extraTag) == true) arguments?.getInt(extraTag, 0) else null
}

fun Fragment.booleanExtraOrException(extraTag: String) : Boolean {
    return arguments.takeIf { it != null && it.containsKey(extraTag) }?.let {
        arguments?.getBoolean(extraTag)
    } ?: throw IllegalArgumentException("Boolean extra is missed.")
}

fun Fragment.booleanExtraOrNull(extraTag: String) : Boolean? {
    return if (arguments?.containsKey(extraTag) == true) arguments?.getBoolean(extraTag) else null
}

fun Fragment.stringExtraOrException(extraTag: String, defaultValue: String = "") : String {
    return arguments.takeIf { it != null && it.containsKey(extraTag) }?.let {
        arguments?.getString(extraTag, defaultValue)
    } ?: throw IllegalArgumentException("Some string extra is missed.")
}

fun Fragment.stringExtraOrNull(extraTag: String) : String? {
    return if (arguments?.containsKey(extraTag) == true) arguments?.getString(extraTag) else null
}

fun Fragment.bundleExtraOrException(extraTag: String) : Bundle {
    return takeIf { arguments?.containsKey(extraTag) == true }?.let {
        arguments?.getBundle(extraTag)
    } ?: throw IllegalArgumentException("Some int extra arg is missed.")
}

fun Fragment.longExtraOrException(extraTag: String) : Long {
    return takeIf { arguments?.containsKey(extraTag) == true }?.let {
        arguments?.getLong(extraTag, 0)
    } ?: throw IllegalArgumentException("Some long extra arg is missed.")
}

fun <T : Parcelable> Fragment.parcelableExtraOrException(extraTag: String): T {
    return takeIf { arguments?.containsKey(extraTag) == true }?.let {
        arguments?.getParcelable<T>(extraTag)
    } ?: throw IllegalArgumentException("Some parcelable extra arg is missed.")
}