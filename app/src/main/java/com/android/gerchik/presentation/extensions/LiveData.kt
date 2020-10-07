package com.android.gerchik.presentation.extensions

import androidx.annotation.NonNull
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import java.util.*

inline fun MutableLiveData<Boolean>?.withProgress(block: () -> Unit) {
    if (this?.value != true) {
        this?.value = true
    }
    block.invoke()
    this?.value = false
}

operator fun MutableLiveData<Boolean>.not() : Boolean {
    return this.value == false
}

val MutableLiveData<String>.valueOrEmpty: String
    get() = value.orEmpty()


fun <T> LiveData<T>.observeNotNull(@NonNull owner: LifecycleOwner, @NonNull observer: (T) -> Unit) {
    observe(owner, Observer { observer.invoke(it) })
}

fun <T> LiveData<T>.observeNotNull(@NonNull owner: LifecycleOwner, @NonNull observer: Observer<in T>) {
    observe(owner, Observer {
        it?.let { observer.onChanged(it) }
    })
}

@Suppress("UNCHECKED_CAST")
fun <T> LiveData<T>.observeNotEquals(
    @NonNull owner: LifecycleOwner,
    equals: (T, T) -> Boolean = { first, second -> Objects.equals(first, second) },
    @NonNull observer: (T) -> Unit
) {
    var oldValue: T? = null
    var isInitialized = false
    observe(owner, Observer {
        if (!isInitialized || !equals(oldValue as T, it)) {
            oldValue = it
            isInitialized = true
            observer.invoke(it)
        }
    })
}

fun <T, Y> LiveData<T>.mapToMutable(mapFunction: (T) -> Y): MutableLiveData<Y> {
    return MediatorLiveData<Y>().apply {
        addSource(this@mapToMutable) { x -> setValue(mapFunction.invoke(x)) }
    }
}