package com.app.zeusapp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Settings : AppCompatActivity() {
    private var theme = 0
    private var backgroundButton = 0
    private var cardBacksideValue = 0
    private var arrow_right = 0
    private var arrow_right_transparency = 0
    private var arrow_left = 0
    private var arrow_left_transparency = 0

    //private String path = "daten.txt";
    private var arrowLeft: Button? = null
    private var choosenBackside: ImageView? = null
    private var arrowRight: Button? = null
    private var setSettings: Button? = null
    private var back: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val settings = getSharedPreferences("app_settings", MODE_PRIVATE)
        theme = settings.getInt("THEME", R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setTheme(theme)
        setContentView(R.layout.activity_settings)
        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red)
        arrow_right = settings.getInt("ARROWRIGHT", R.drawable.arrow_right_red)
        arrow_right_transparency =
            settings.getInt("ARROWRIGHT_TRA", R.drawable.arrow_right_red_transparency)
        arrow_left = settings.getInt("ARROWLEFT", R.drawable.arrow_left_red)
        arrow_left_transparency =
            settings.getInt("ARROWLEFT_TRA", R.drawable.arrow_left_red_transparency)
        choosenBackside = findViewById<View>(R.id.backsides) as ImageView
        arrowLeft = findViewById<View>(R.id.leftArrow) as Button
        arrowRight = findViewById<View>(R.id.RightArrow) as Button
        setSettings = findViewById<View>(R.id.setSettings) as Button
        setSettings!!.setBackgroundResource(backgroundButton)

        back = findViewById<View>(R.id.back) as Button
        back!!.setBackgroundResource(backgroundButton)
        cardBacksideValue = settings.getInt("BACKCARDVALUE", 0)
        initButtonColor(cardBacksideValue)
        setSettings!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // change color
                setSettings!!.setBackgroundResource(R.drawable.button_highlight)
                setSettings(v)
            } else if (event.action == MotionEvent.ACTION_UP) {
                // set to normal color
                setSettings!!.setBackgroundResource(backgroundButton)
            }
            true
        }
        arrowLeft!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // change color
                if (cardBacksideValue != 0) {
                    arrowLeft!!.setBackgroundResource(R.drawable.arrow_left_highlight)
                    linksButton(v)
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                // set to normal color
                if (cardBacksideValue != 0) {
                    arrowLeft!!.setBackgroundResource(arrow_left)
                }
            }
            true
        }
        arrowRight!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // change color
                if (cardBacksideValue != 3) {
                    arrowRight!!.setBackgroundResource(R.drawable.arrow_right_highlight)
                    rechtsButton(v)
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                // set to normal color
                if (cardBacksideValue != 3) {
                    arrowRight!!.setBackgroundResource(arrow_right)
                }
            }
            true
        }
        back!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // change color
                back!!.setBackgroundResource(R.drawable.button_highlight)
                goBacktoMenu(v)
            } else if (event.action == MotionEvent.ACTION_UP) {
                // set to normal color
                back!!.setBackgroundResource(backgroundButton)
            }
            true
        }

    }

    fun linksButton(v: View?) {
        when (cardBacksideValue) {
            1 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_red)
                arrowLeft!!.setBackgroundResource(arrow_left_transparency)
                cardBacksideValue--
            }

            2 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_blue)
                cardBacksideValue--
            }

            3 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_green)
                arrowRight!!.setBackgroundResource(arrow_right)
                cardBacksideValue--
            }

            else -> {}
        }
    }

    fun rechtsButton(v: View?) {
        when (cardBacksideValue) {
            0 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_blue)
                arrowLeft!!.setBackgroundResource(arrow_left)
                cardBacksideValue++
            }

            1 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_green)
                cardBacksideValue++
            }

            2 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_gray)
                arrowRight!!.setBackgroundResource(arrow_right_transparency)
                cardBacksideValue++
            }

            else -> {}
        }
    }

    fun setSettings(v: View?) {

        val settings = getSharedPreferences("app_settings", MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("BACKCARDVALUE", cardBacksideValue)
        editor.putInt("THEME", getTheme(cardBacksideValue))
        editor.putInt("BUTTONCOLOR", getColorButton(cardBacksideValue)[0])
        editor.putInt("ARROWRIGHT", getColorButton(cardBacksideValue)[1])
        editor.putInt("ARROWRIGHT_TRA", getColorButton(cardBacksideValue)[2])
        editor.putInt("ARROWLEFT", getColorButton(cardBacksideValue)[3])
        editor.putInt("ARROWLEFT_TRA", getColorButton(cardBacksideValue)[4])
        editor.putInt("FOUR", getColorButton(cardBacksideValue)[5])
        editor.putInt("SIX", getColorButton(cardBacksideValue)[6])
        editor.putInt("EIGHT", getColorButton(cardBacksideValue)[7])
        editor.putInt("TEN", getColorButton(cardBacksideValue)[8])
        editor.putInt("TWELVE", getColorButton(cardBacksideValue)[9])
        editor.putInt("SIXTEEN", getColorButton(cardBacksideValue)[10])
        editor.putInt("TWENTY", getColorButton(cardBacksideValue)[11])
        editor.apply()
        Toast.makeText(this, "Einstellung gespeichert!", Toast.LENGTH_LONG).show()
        val intent = intent
        finish()
        startActivity(intent)
    }



    fun goBacktoMenu(view: View?) {
        val `in` = Intent(this, MainActivity::class.java)
        this.startActivity(`in`)
        finish()
    }


    fun getTheme(n: Int): Int {
        var r = -1
        when (n) {
            0 -> r = R.style.AppTheme
            1 -> r = R.style.BlueAppTheme
            2 -> r = R.style.GreenAppTheme
            3 -> r = R.style.GrayAppTheme
            else -> {}
        }
        return r
    }

    fun getColorButton(n: Int): IntArray {
        val r = IntArray(12)
        when (n) {
            0 -> {
                r[0] = R.drawable.button_red
                r[1] = R.drawable.arrow_right_red
                r[2] = R.drawable.arrow_right_red_transparency
                r[3] = R.drawable.arrow_left_red
                r[4] = R.drawable.arrow_left_red_transparency
                r[5] = R.drawable.four_red
                r[6] = R.drawable.six_red
                r[7] = R.drawable.eight_red
                r[8] = R.drawable.ten_red
                r[9] = R.drawable.twelve_red
                r[10] = R.drawable.sixteen_red
                r[11] = R.drawable.twenty_red
            }

            1 -> {
                r[0] = R.drawable.button_blue
                r[1] = R.drawable.arrow_right_blue
                r[2] = R.drawable.arrow_right_blue_transparency
                r[3] = R.drawable.arrow_left_blue
                r[4] = R.drawable.arrow_left_blue_transparency
                r[5] = R.drawable.four_blue
                r[6] = R.drawable.six_blue
                r[7] = R.drawable.eight_blue
                r[8] = R.drawable.ten_blue
                r[9] = R.drawable.twelve_blue
                r[10] = R.drawable.sixteen_blue
                r[11] = R.drawable.twenty_blue
            }

            2 -> {
                r[0] = R.drawable.button_green
                r[1] = R.drawable.arrow_right_green
                r[2] = R.drawable.arrow_right_green_transparency
                r[3] = R.drawable.arrow_left_green
                r[4] = R.drawable.arrow_left_green_transparency
                r[5] = R.drawable.four_green
                r[6] = R.drawable.six_green
                r[7] = R.drawable.eight_green
                r[8] = R.drawable.ten_green
                r[9] = R.drawable.twelve_green
                r[10] = R.drawable.sixteen_green
                r[11] = R.drawable.twenty_green
            }

            3 -> {
                r[0] = R.drawable.button_gray
                r[1] = R.drawable.arrow_right_gray
                r[2] = R.drawable.arrow_right_gray_transparency
                r[3] = R.drawable.arrow_left_gray
                r[4] = R.drawable.arrow_left_gray_transparency
                r[5] = R.drawable.four_gray
                r[6] = R.drawable.six_gray
                r[7] = R.drawable.eight_gray
                r[8] = R.drawable.ten_gray
                r[9] = R.drawable.twelve_gray
                r[10] = R.drawable.sixteen_gray
                r[11] = R.drawable.twenty_gray
            }

            else -> {}
        }
        return r
    }

    fun initButtonColor(n: Int) {
        when (n) {
            0 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_red)
                arrowLeft!!.setBackgroundResource(arrow_left_transparency)
                arrowRight!!.setBackgroundResource(arrow_right)
            }

            1 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_blue)
                arrowLeft!!.setBackgroundResource(arrow_left)
                arrowRight!!.setBackgroundResource(arrow_right)
            }

            2 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_green)
                arrowLeft!!.setBackgroundResource(arrow_left)
                arrowRight!!.setBackgroundResource(arrow_right)
            }

            3 -> {
                choosenBackside!!.setImageResource(R.drawable.backside_gray)
                arrowLeft!!.setBackgroundResource(arrow_left)
                arrowRight!!.setBackgroundResource(arrow_right_transparency)
            }

            else -> {}
        }
    }
}

