package com.app.zeusapp
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var backgroundButton = 0
    private var toPlayMenu: Button? = null
    private var toSettingsMenu: Button? = null
    private var quit: Button? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        val settings = getSharedPreferences("app_settings", MODE_PRIVATE)
        val theme = settings.getInt("THEME", R.style.AppTheme)
        setTheme(theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red)
        toPlayMenu = findViewById<Button>(R.id.toPlayMenu)?.apply {
            setBackgroundResource(backgroundButton)
            setOnTouchListener(OnTouchListener { v: View?, event: MotionEvent ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    setBackgroundResource(R.drawable.button_highlight)
                    toPlayMenu(v)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    setBackgroundResource(backgroundButton)
                }
                true
            })
        }
        toSettingsMenu = findViewById<Button>(R.id.toSettingsMenu)?.apply {
            setBackgroundResource(backgroundButton)
            setOnTouchListener(OnTouchListener { v: View?, event: MotionEvent ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    setBackgroundResource(R.drawable.button_highlight)
                    toSettingsMenu(v)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    setBackgroundResource(backgroundButton)
                }
                true
            })
        }
        quit = findViewById<Button>(R.id.quit)?.apply {
            setBackgroundResource(backgroundButton)
            setOnTouchListener(OnTouchListener { v: View?, event: MotionEvent ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    setBackgroundResource(R.drawable.button_highlight)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    setBackgroundResource(backgroundButton)
                    quit(v)
                }
                true
            })
        }
    }

    fun toPlayMenu(v: View?) {
        val a = Intent(this, FirstMenu::class.java)
        startActivity(a)
        finish()
    }

    fun toSettingsMenu(v: View?) {
        val `in` = Intent(this, Settings::class.java)
        startActivity(`in`)
        finish()
    }

    fun quit(v: View?) {
        finish()
    }
}
