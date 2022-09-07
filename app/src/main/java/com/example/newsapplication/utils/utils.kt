package com.example.newsapplication.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.makeToast(message: String) { Toast.makeText(context, message, Toast.LENGTH_LONG).show() }

fun View.show() { visibility = View.VISIBLE }

fun show(vararg views: View) { views.forEach { it.show() } }

fun View.hide() { visibility = View.GONE }

fun hide(vararg views: View) { views.forEach { it.hide() } }

fun View.invisible() { visibility = View.INVISIBLE }

fun EditText.clear() { text.clear() }

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
