package com.example.mathapp.ui.common

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.launchViewIntent(url: String) {
    val intent = Intent(Intent.ACTION_VIEW).setData(url.toUri())
    startActivity(intent)
}
