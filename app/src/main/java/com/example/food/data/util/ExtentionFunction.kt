package com.example.food.data.util

import android.view.View
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


fun View?.toVisible() {
    if (this?.visibility != View.VISIBLE)
        this?.visibility = View.VISIBLE
}

fun View?.toGone() {
    if (this?.visibility != View.GONE)
        this?.visibility = View.GONE
}

fun View?.toInvisible() {
    if (this?.visibility != View.INVISIBLE)
        this?.visibility = View.INVISIBLE
}


fun String?.validString() = this != null && this.isNotEmpty()


fun <T : Any> CoroutineScope.safeLaunch(
    liveData: MutableLiveData<Resource<T>>,
    coroutineBlock: suspend CoroutineScope.() -> Unit
): Job {
    return this.launch(CoroutineExceptionHandler{ _, throwable ->
        throwable.printStackTrace()
        liveData.postValue(Resource.Error(throwable.toString()))
    }, block = coroutineBlock)

}
