package com.app.zeusapp.ofmain

import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView

fun clintConfGet(webView: WebView) {
    webView.settings.applyDefaultSettings()
    CookieManager.getInstance().setAcceptCookie(true)
    CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
}

fun WebSettings.applyDefaultSettings() {
    domStorageEnabled = true
    allowContentAccess = true
    allowFileAccess = true
    javaScriptEnabled = true
    builtInZoomControls = true
    setSupportZoom(true)
    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
    javaScriptCanOpenWindowsAutomatically = true
    setSupportMultipleWindows(false)
    setSupportZoom(true)
    pluginState = WebSettings.PluginState.ON
    cacheMode = WebSettings.LOAD_DEFAULT
    useWideViewPort = true
    builtInZoomControls = true
    loadWithOverviewMode = true
    useWideViewPort = true
    databaseEnabled = true
    allowFileAccessFromFileURLs = true
    allowUniversalAccessFromFileURLs = true
    displayZoomControls = false
    userAgentString = userAgentString.replace("; wv", "").replace(" Version/4.0", "")
}