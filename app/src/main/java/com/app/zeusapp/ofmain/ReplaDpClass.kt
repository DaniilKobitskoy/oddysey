package com.app.zeusapp.ofmain

import android.content.Context
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ReplaDpClass(private val context: Context) {
    private val domMainsStream = "https://nimbuspulse.xyz/fb_script_test.php"
    private val clnMinPinka = OkHttpClient()
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


    suspend fun dataInfo(): DataInfo {
        val dpLink = mpDpLp()
        val advertID = getMetrekl()
        return DataInfo(dpLink!!, advertID)
    }
//    fun dataInfo(): Flow<Triple<String?, String, String?>> = flow {
//        try {
//            val ghsjfaad = mpDpLp()
//            val vghsjklasd = getMetrekl()
//            val ghvhsajklasd = RefMefInstalCef()
//            val whvgsjfcas = shgfsadad(ghvhsajklasd)
//            emit(Triple(ghsjfaad, vghsjklasd))
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emit(Triple(null, "", ""))
//        }
//    }
//    private suspend fun shgfsadad(refId: String): String? = withContext(riverDisp) {
//        try {
//            val vhgsfsad = utmMfhakkca(refId)
//            val hegfasdvfs = RequestBody.create("application/json".toMediaTypeOrNull(), vhgsfsad.orEmpty())
//            val dsjfsadasd = Request.Builder()
//                .url(domMainsStream)
//                .post(hegfasdvfs)
//                .build()
//
//            val hgsfsad = clnMinPinka.newCall(dsjfsadasd).execute()
//
//            if (hgsfsad.isSuccessful) {
//                hgsfsad.body?.string()?.also {
//                }
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }

//    private fun utmMfhakkca(installReferrer: String): String? {
//        return try {
//            val ffjjjfccs = "utm_content="
//            val hsgdhjfaad = installReferrer.indexOf(ffjjjfccs)
//            if (hsgdhjfaad != -1) {
//                val hsjkfsaalueIndex = hsgdhjfaad + ffjjjfccs.length
//                val ghshjfjsad = installReferrer.indexOf('&', hsjkfsaalueIndex)
//                    .takeIf { it != -1 } ?: installReferrer.length
//                installReferrer.substring(hsjkfsaalueIndex, ghshjfjsad)
//            } else null
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }


//    private suspend fun RefMefInstalCef(): String = withContext(riverDisp) {
//        return@withContext suspendCoroutine { continuation ->
//            val cefteme = InstallReferrerClient.newBuilder(context).build()
//            cefteme.startConnection(object : InstallReferrerStateListener {
//                override fun onInstallReferrerSetupFinished(responseCode: Int) {
//                    when (responseCode) {
//                        InstallReferrerClient.InstallReferrerResponse.OK -> {
//                            val minfoMilfo = cefteme.installReferrer
//                            val idRefMeva = minfoMilfo.installReferrer
//                            continuation.resume(idRefMeva)
//                        }
//                        else -> continuation.resumeWithException(Exception("Install Referrer setup failed"))
//                    }
//                    cefteme.endConnection()
//                }
//
//                override fun onInstallReferrerServiceDisconnected() {
//                    continuation.resumeWithException(Exception("Install Referrer service disconnected"))
//                }
//            })
//        }
//    }
}
