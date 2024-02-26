package com.app.zeusapp.ofmain

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.app.zeusapp.MainActivity

class WebSetListMain(private val confextfad: Context, private val VieSetMina: WebView, private val mainUrl: String) : WebViewClient() {

    private val utimytiKyti = LinkStep(confextfad)

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        VieSetMina.visibility = View.VISIBLE
        utimytiKyti.opDomWeb(url.toString())
    }

    fun getSaveOfUrl(url: String): String? {
        return utimytiKyti.LaGetStDom(url)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val purlLast = request?.url.toString()
        if (mainUrl != purlLast){
            VieSetMina.visibility = View.VISIBLE
            utimytiKyti.opDomWeb(request?.url.toString())
            if (!chekDomenId(purlLast)) {
                val packDack = Intent(Intent.ACTION_VIEW, Uri.parse(purlLast))
                if (packDack.resolveActivity(confextfad.packageManager) != null) {
                    confextfad.startActivity(packDack)
                }
                return true
            }
            view?.loadUrl(purlLast)
        }else{
            confextfad.startActivity(Intent(confextfad, MainActivity::class.java))
        }

        return false
    }

    private fun chekDomenId(url: String): Boolean {
        return url.startsWith("http://") || url.startsWith("https://")
    }
}