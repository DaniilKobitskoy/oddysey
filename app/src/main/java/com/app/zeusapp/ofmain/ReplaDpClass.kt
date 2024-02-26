package com.app.zeusapp.ofmain

import android.content.Context
import android.content.Intent
import android.util.Log
import com.app.zeusapp.MainActivity
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ReplaDpClass(private val context: Context) {
    private val riverDisp = Dispatchers.IO

    private suspend fun mpDpLp(): String? = withContext(Dispatchers.Main) {
        return@withContext suspendCoroutine<String?> { continuation ->
            AppLinkData.fetchDeferredAppLinkData(context) { appLinkData: AppLinkData? ->
                val plinkDp = appLinkData?.targetUri?.host
                continuation.resume(plinkDp)
            }
        }
    }
    private suspend fun getMetrekl(): String = withContext(riverDisp) {
        AdvertisingIdClient.getAdvertisingIdInfo(context).id.toString()
    }


    suspend fun dataInfo(): DataInfo? {
        val dpLink = mpDpLp()
        val advertID = getMetrekl()

        if (dpLink != null) {
            Log.d("ReplaDpClass", "dpLink is not null: $dpLink")
            return DataInfo(dpLink, advertID)
        } else {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
            return null
        }
    }


}
