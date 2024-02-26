package com.app.zeusapp.ofmain


import android.content.Context


class LinkStep(context: Context) {

    private val DATA_PREF = "${context.packageName}_prefs"
    private val FIRST_LAST_END_L = "domenData"
    var domL = ""
    val sPref = context.getSharedPreferences(DATA_PREF, Context.MODE_PRIVATE)

    fun opDomWeb(url: String) {
        if(domL == ""){
            domL = LaGetStDom(url).toString()
            sPref.edit().putString(FIRST_LAST_END_L, url).apply()
        }
    }

    fun LaGetStDom(url: String): String? {
        return sPref.getString(FIRST_LAST_END_L, url)
    }
}