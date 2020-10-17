package com.dc.offlinefirstarchitecture.ui.core

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    open fun showLoading() {
        Toast.makeText(this, "Show Loading", Toast.LENGTH_SHORT).show()
    }

    open fun hideLoading() {
        Toast.makeText(this, "Hide Loading", Toast.LENGTH_SHORT).show()
    }

    fun showNetworkNotConnected() {
        Toast.makeText(this, "Network Not available", Toast.LENGTH_SHORT).show()
    }

    fun showMessage(message: String) {
        showToast(message)
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}