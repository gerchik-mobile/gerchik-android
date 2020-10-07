package com.android.gerchik.presentation.utils.adapterdelegates

import com.hannesdorfmann.adapterdelegates4.AdapterDelegate

internal data class DiffListItemAdapterDelegate<I> (
    val diffItemCallback: DiffUtilCallbackDelegate<I>,
    val delegate: AdapterDelegate<List<I>>
)
