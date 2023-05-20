package com.example.eventplanner.ui.utils

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastViewer @Inject constructor(private val context: Context) {

    fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}