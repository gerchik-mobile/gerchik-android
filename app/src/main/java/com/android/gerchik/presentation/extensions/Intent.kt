package com.android.gerchik.presentation.extensions

import android.content.Intent

fun Intent?.intExtraOrNull(extraTag: String) = if (this != null && hasExtra(extraTag)) getIntExtra(extraTag, 0) else null