package com.app.zeusapp.ofmain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.app.zeusapp.R


class MainViewLaunch : AppCompatActivity() {
    private lateinit var backapsFolka: WebView
    private lateinit var viewHalper: ViewHalper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_client)
        backapsFolka = findViewById(R.id.webView)
        val domPonmka = intent.getStringExtra("DOMEN_MAIN_BUNDLE")
        viewHalper =
            ViewHalper(this@MainViewLaunch, backapsFolka, domPonmka)
        backapsFolka.webChromeClient = viewHalper.setMat
        backapsFolka.webViewClient = viewHalper.listMistkjsds
        viewHalper.nextStep()
        backapsFolka.loadUrl(viewHalper.listMistkjsds.getSaveOfUrl(domPonmka.toString()).toString())
    }
    override fun onBackPressed() {
        if (backapsFolka.canGoBack()) {
            backapsFolka.goBack()
        } else {
            super.onBackPressed()
        }
    }
}