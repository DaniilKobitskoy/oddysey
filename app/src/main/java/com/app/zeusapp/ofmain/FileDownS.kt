package com.app.zeusapp.ofmain


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.webkit.PermissionRequest
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class FileDownS(private val ViewActMains: AppCompatActivity) : WebChromeClient() {

    private var mesViews: ValueCallback<Array<Uri>>? = null
    private var resultMess: ActivityResultLauncher<Intent>? = null

    companion object {
        const val FILECHOOSER_RESULTCODE = 1
    }

    init {
        smActive()
    }

    override fun onShowFileChooser(
        webView: WebView?,
        filePathCallback: ValueCallback<Array<Uri>>?,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        if (mesViews != null) {
            mesViews?.onReceiveValue(null)
            mesViews = null
        }
        mesViews = filePathCallback
        val pintent = Intent(Intent.ACTION_GET_CONTENT)
        pintent.addCategory(Intent.CATEGORY_OPENABLE)
        pintent.type = "*/*"
        resultMess?.launch(Intent.createChooser(pintent, "File Browser"))
        return true
    }

    override fun onPermissionRequest(request: PermissionRequest) {
        request.grant(request.resources)
        if (ContextCompat.checkSelfPermission(ViewActMains, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ViewActMains, arrayOf(Manifest.permission.CAMERA), 1)
        }
    }

    private fun smActive() {
        resultMess = ViewActMains.registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == AppCompatActivity.RESULT_OK) {
                val gjyri: Uri? = result.data?.data
                if (gjyri != null && mesViews != null) {
                    mesViews?.onReceiveValue(arrayOf(gjyri))
                    mesViews = null
                }
            } else {
                mesViews?.onReceiveValue(null)
                mesViews = null
            }
        }
    }
}
