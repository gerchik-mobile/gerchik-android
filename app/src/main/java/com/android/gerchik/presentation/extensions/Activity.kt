package com.android.gerchik.presentation.extensions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Handler
import android.os.Parcelable
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.fragment.app.FragmentActivity
import com.android.gerchik.presentation.common.StringResource
import java.io.Serializable
import java.lang.IllegalArgumentException

fun AppCompatActivity.makeToolbarAsActionBar(toolbar: Toolbar) {
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(false)
}

fun AppCompatActivity.setToolbarTitle(title: String) {
    supportActionBar?.title = title
}

fun AppCompatActivity.setToolbarTitle(title: StringResource?) {
    supportActionBar?.title = getString(title)
}

fun AppCompatActivity.enableBackButton(@DrawableRes icon: Int) {
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    supportActionBar?.setHomeAsUpIndicator(icon)
}

fun FragmentActivity.hideSoftKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)
        ?.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
}

fun FragmentActivity.post(r: () -> Unit) {
    Handler().post(r)
}

fun FragmentActivity.postDelayed(delay: Long, r: () -> Unit) {
    Handler().postDelayed(r, delay)
}

fun FragmentActivity.isPermissionGranted(permission: String) : Boolean {
    return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

/**
 * Message extensions.
 * TODO change to application style implementation
 */
fun Activity.showMessage(errorMessageRes: StringResource) {
    Toast.makeText(this, getString(errorMessageRes), Toast.LENGTH_SHORT).show()
}

fun Activity.showMessage(errorMessage: String) {
    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
}

/**
 * For activity argument handling.
 */
fun FragmentActivity.intExtraOrException(extraTag: String) : Int {
    return if (intent.hasExtra(extraTag)) {
        intent.getIntExtra(extraTag, 0)
    } else {
        throw IllegalArgumentException("Some int extra arg is missed.")
    }
}

fun FragmentActivity.intExtraOrNull(extraTag: String) : Int? {
    return intent.intExtraOrNull(extraTag)
}

fun FragmentActivity.booleanExtraOrException(extraTag: String) : Boolean {
    return if (intent.hasExtra(extraTag)) {
        intent.getBooleanExtra(extraTag, false)
    } else {
        throw IllegalArgumentException("Some boolean extra arg is missed.")
    }
}

fun FragmentActivity.booleanExtraOrNull(extraTag: String) : Boolean? {
    return if (intent.hasExtra(extraTag)) intent.getBooleanExtra(extraTag, false) else null
}

fun FragmentActivity.stringExtraOrException(extraTag: String) : String {
    return takeIf { intent.hasExtra(extraTag) }?.let {
        intent.getStringExtra(extraTag)
    } ?: throw IllegalArgumentException("Some string extra arg is missed.")
}

fun FragmentActivity.stringExtraOrNull(extraTag: String) : String? {
    return intent.getStringExtra(extraTag)
}

fun <T : Parcelable> FragmentActivity.parcelableExtraOrException(extraTag: String): T {
    return takeIf { intent.hasExtra(extraTag) }?.let {
        intent.getParcelableExtra<T>(extraTag)
    } ?: throw IllegalArgumentException("Some parcelable extra arg is missed.")
}

fun <T : Parcelable> FragmentActivity.parcelableExtraOrNull(extraTag: String): T? {
    return  if (intent.hasExtra(extraTag)) intent.getParcelableExtra<T>(extraTag) else null
}

fun <T : Parcelable> FragmentActivity.parcelableArrayListExtra(extraTag: String): ArrayList<T> {
    return takeIf { intent.hasExtra(extraTag) }?.let {
        intent.getParcelableArrayListExtra<T>(extraTag)
    } ?: throw IllegalArgumentException("Some parcelable array extra arg is missed.")
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> FragmentActivity.serializableExtraOrException(extraTag: String): T {
    return takeIf { intent.hasExtra(extraTag) }?.let {
        intent.getSerializableExtra(extraTag) as T
    } ?: throw IllegalArgumentException("Some parcelable extra arg is missed.")
}