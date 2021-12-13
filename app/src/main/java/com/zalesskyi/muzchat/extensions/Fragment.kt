package com.zalesskyi.muzchat.extensions

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.toast(stringRes: Int) {
    Toast.makeText(requireContext(), stringRes, Toast.LENGTH_SHORT).show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}