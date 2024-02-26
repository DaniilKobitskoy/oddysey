package com.app.zeusapp.ofmain

import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class ViewHalper(private val viewActivity: AppCompatActivity, private val viewWindow: WebView, private val linka: String?) {
    val listMistkjsds = WebSetListMain(viewActivity, viewWindow, linka!!)
    val setMat = FileDownS(viewActivity)

    fun nextStep() {
        with(viewWindow) {
            webChromeClient = setMat
            webViewClient = listMistkjsds
            clintConfGet(this)
        }
    }
}
