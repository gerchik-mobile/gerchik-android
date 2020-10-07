package com.android.gerchik.presentation.utils.adapterdelegates

import androidx.recyclerview.widget.DiffUtil

internal abstract class DiffUtilCallbackDelegate<T>: DiffUtil.ItemCallback<T>() {
    abstract fun isForViewType(data: T): Boolean
}