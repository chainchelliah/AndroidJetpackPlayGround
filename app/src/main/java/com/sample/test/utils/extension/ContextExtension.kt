package com.sample.test.utils.extension

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast


fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.isNetworkAvailable(): Boolean {
    val manager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return manager.activeNetworkInfo?.isConnected ?: false
}
