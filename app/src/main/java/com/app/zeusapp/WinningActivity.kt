package com.app.zeusapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WinningActivity : AppCompatActivity() {
    private var backgroundButton = 0
    private var amountOfCards = 0
    private var numberOfBacksideColor = 0
    private var again: Button? = null
    private var toMenu: Button? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        val settings = getSharedPreferences("app_settings", MODE_PRIVATE)
        val theme = settings.getInt("THEME", R.style.AppTheme)
        setTheme(theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winning)
        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red)
        again = findViewById<Button>(R.id.againButton)?.apply {
            setBackgroundResource(backgroundButton)
            setOnTouchListener(OnTouchListener { v: View?, event: MotionEvent ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    // change color
                    setBackgroundResource(R.drawable.button_highlight)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    // set to normal color
                    setBackgroundResource(backgroundButton)
                    playAgain(v)
                }
                true
            })
        }
        toMenu = findViewById<Button>(R.id.backToMainMenu)?.apply {
            setBackgroundResource(backgroundButton)
            setOnTouchListener(OnTouchListener { v: View?, event: MotionEvent ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    // change color
                    setBackgroundResource(R.drawable.button_highlight)
                } else if (event.action == MotionEvent.ACTION_UP) {
                    // set to normal color
                    setBackgroundResource(backgroundButton)
                    toMenu(v)
                }
                true
            })
        }
        amountOfCards = settings.getInt("CARDAMOUNT", 4)
        numberOfBacksideColor = settings.getInt("BACKCARDVALUE", 0)
    }

    fun playAgain(view: View?) {
        val intent = Intent(this@WinningActivity, GameActivity::class.java)
        val am = getAmounts(amountOfCards)
        intent.putExtra("ROWS", am[0])
        intent.putExtra("COLUMS", am[1])
        intent.putExtra("BACKSIDE", getResourceBackcard(numberOfBacksideColor))
        startActivity(intent)
        finish()
    }

    fun toMenu(view: View?) {
        val intent = Intent(this@WinningActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun getAmounts(n: Int): IntArray {
        val amountArray = IntArray(2)
        when (n) {
            4 -> {
                amountArray[0] = 2
                amountArray[1] = 2
            }

            6 -> {
                amountArray[0] = 2
                amountArray[1] = 3
            }

            8 -> {
                amountArray[0] = 2
                amountArray[1] = 4
            }

            10 -> {
                amountArray[0] = 5
                amountArray[1] = 2
            }

            12 -> {
                amountArray[0] = 4
                amountArray[1] = 3
            }

            16 -> {
                amountArray[0] = 4
                amountArray[1] = 4
            }

            20 -> {
                amountArray[0] = 5
                amountArray[1] = 4
            }

            else -> {}
        }
        return amountArray
    }

    fun getResourceBackcard(n: Int): Int {
        val r: Int
        r = when (n) {
            0 -> R.drawable.backside_red
            1 -> R.drawable.backside_blue
            2 -> R.drawable.backside_green
            3 -> R.drawable.backside_gray
            else -> -1
        }
        return r
    }
}
