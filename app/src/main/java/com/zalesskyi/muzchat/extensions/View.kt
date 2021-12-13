package com.zalesskyi.muzchat.extensions

import android.view.View
import androidx.annotation.IdRes
import androidx.core.view.ViewCompat

inline fun <V : View> View.requireViewByIdCompat(@IdRes id: Int): V {
    return ViewCompat.requireViewById(this, id)
}