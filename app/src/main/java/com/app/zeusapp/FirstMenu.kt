package com.app.zeusapp

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class FirstMenu : AppCompatActivity() {
    // private String path = "daten.txt";
    // private int n;
    private var backgroundButton = R.drawable.button_red
    private var theme = 0
    private var arrow_right = 0
    private var arrow_right_transparency = 0
    private var arrow_left = 0
    private var arrow_left_transparency = 0
    private var four = 0
    private var six = 0
    private var eight = 0
    private var ten = 0
    private var twelve = 0
    private var sixteen = 0
    private var twenty = 0
    private var rightArrow: Button? = null
    private var leftArrow: Button? = null
    private var ok: Button? = null
    private var back: Button? = null
    private var cardBacksideAmount = 0
    private var cardNumberImage: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        val settings = getSharedPreferences("app_settings", MODE_PRIVATE)
        theme = settings.getInt("THEME", R.style.AppTheme)
        setTheme(theme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_menu)
        backgroundButton = settings.getInt("BUTTONCOLOR", R.drawable.button_red)
        arrow_right = settings.getInt("ARROWRIGHT", R.drawable.arrow_right_red)
        arrow_right_transparency =
            settings.getInt("ARROWRIGHT_TRA", R.drawable.arrow_right_red_transparency)
        arrow_left = settings.getInt("ARROWLEFT", R.drawable.arrow_left_red)
        arrow_left_transparency =
            settings.getInt("ARROWLEFT_TRA", R.drawable.arrow_left_red_transparency)
        four = settings.getInt("FOUR", R.drawable.four_red)
        six = settings.getInt("SIX", R.drawable.six_red)
        eight = settings.getInt("EIGHT", R.drawable.eight_red)
        ten = settings.getInt("TEN", R.drawable.ten_red)
        twelve = settings.getInt("TWELVE", R.drawable.twelve_red)
        sixteen = settings.getInt("SIXTEEN", R.drawable.sixteen_red)
        twenty = settings.getInt("TWENTY", R.drawable.twenty_red)
        cardBacksideAmount = settings.getInt("CARDAMOUNT", 4)
        rightArrow = findViewById<View>(R.id.arrowRight) as Button
        leftArrow = findViewById<View>(R.id.arrowLeft) as Button
        ok = findViewById<View>(R.id.playGame) as Button
        ok!!.setBackgroundResource(backgroundButton)
        back = findViewById<View>(R.id.back) as Button
        back!!.setBackgroundResource(backgroundButton)
        cardNumberImage = findViewById<View>(R.id.cardAmount) as ImageView
        initMenu(cardBacksideAmount)
        initCurrentCardAmountColor(cardBacksideAmount)
        leftArrow!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // change color
                if (cardBacksideAmount != 4) {
                    leftArrow!!.setBackgroundResource(R.drawable.arrow_left_highlight)
                    leftArrowOnClick(v)
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                // set to normal color
                if (cardBacksideAmount != 4) {
                    leftArrow!!.setBackgroundResource(arrow_left)
                }
            }
            true
        }
        rightArrow!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // change color
                if (cardBacksideAmount != 20) {
                    rightArrow!!.setBackgroundResource(R.drawable.arrow_right_highlight)
                    rightArrowOnClick(v)
                }
            } else if (event.action == MotionEvent.ACTION_UP) {
                // set to normal color
                if (cardBacksideAmount != 20) {
                    rightArrow!!.setBackgroundResource(arrow_right)
                }
            }
            true
        }
        ok!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // change color
                ok!!.setBackgroundResource(R.drawable.button_highlight)
                playGame(v)
            } else if (event.action == MotionEvent.ACTION_UP) {
                // set to normal color
                ok!!.setBackgroundResource(backgroundButton)
            }
            true
        }
        back!!.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                // change color
                back!!.setBackgroundResource(R.drawable.button_highlight)
                back(v)
            } else if (event.action == MotionEvent.ACTION_UP) {
                // set to normal color
                back!!.setBackgroundResource(backgroundButton)
            }
            true
        }
    }

    /* Original methode by Ulf Grösch and modified by Ramón Wilhelm */
    fun leftArrowOnClick(v: View?) {
        when (cardBacksideAmount) {
            6 -> {
                cardNumberImage!!.setImageResource(four)
                leftArrow!!.setBackgroundResource(arrow_left_transparency)
                cardBacksideAmount -= 2
            }

            8 -> {
                cardNumberImage!!.setImageResource(six)
                cardBacksideAmount -= 2
            }

            10 -> {
                cardNumberImage!!.setImageResource(eight)
                cardBacksideAmount -= 2
            }

            12 -> {
                cardNumberImage!!.setImageResource(ten)
                cardBacksideAmount -= 2
            }

            16 -> {
                cardNumberImage!!.setImageResource(twelve)
                cardBacksideAmount -= 4
            }

            20 -> {
                cardNumberImage!!.setImageResource(sixteen)
                rightArrow!!.setBackgroundResource(arrow_right)
                cardBacksideAmount -= 4
            }

            else -> {}
        }
    }

    /* Original methode by Ulf Grösch and modified by Ramón Wilhelm */
    fun rightArrowOnClick(v: View?) {
        when (cardBacksideAmount) {
            4 -> {
                cardNumberImage!!.setImageResource(six)
                leftArrow!!.setBackgroundResource(arrow_left)
                cardBacksideAmount += 2
            }

            6 -> {
                cardNumberImage!!.setImageResource(eight)
                cardBacksideAmount += 2
            }

            8 -> {
                cardNumberImage!!.setImageResource(ten)
                cardBacksideAmount += 2
            }

            10 -> {
                cardNumberImage!!.setImageResource(twelve)
                cardBacksideAmount += 2
            }

            12 -> {
                cardNumberImage!!.setImageResource(sixteen)
                cardBacksideAmount += 4
            }

            16 -> {
                cardNumberImage!!.setImageResource(twenty)
                rightArrow!!.setBackgroundResource(arrow_right_transparency)
                cardBacksideAmount += 4
            }

            else -> {}
        }
    }

    fun playGame(v: View?) {
        /*
        File f = new File (path);
        if (!f.exists() && !f.isDirectory())
        {
            n = Integer.parseInt(laden());
        }*/
        val i = Intent(this@FirstMenu, GameActivity::class.java)
        val settings = getSharedPreferences("app_settings", MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("CARDAMOUNT", cardBacksideAmount)
        editor.apply()
        val amount = getAmounts(cardBacksideAmount)
        i.putExtra("ROWS", amount[0])
        i.putExtra("COLUMS", amount[1])
        i.putExtra("BACKSIDE", getResourceBackcard(settings.getInt("BACKCARDVALUE", 0)))
        this.startActivity(i)
        finish()
    }

    fun back(v: View?) {
        val `in` = Intent(this, MainActivity::class.java)
        finish()
        this.startActivity(`in`)
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

            else -> {
                amountArray[0] = 0
                amountArray[1] = 0
            }
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

    fun initCurrentCardAmountColor(n: Int) {
        when (n) {
            4 -> {
                leftArrow!!.setBackgroundResource(arrow_left_transparency)
                rightArrow!!.setBackgroundResource(arrow_right)
                cardNumberImage!!.setImageResource(four)
            }

            6 -> {
                leftArrow!!.setBackgroundResource(arrow_left)
                rightArrow!!.setBackgroundResource(arrow_right)
                cardNumberImage!!.setImageResource(six)
            }

            8 -> {
                leftArrow!!.setBackgroundResource(arrow_left)
                rightArrow!!.setBackgroundResource(arrow_right)
                cardNumberImage!!.setImageResource(eight)
            }

            10 -> {
                leftArrow!!.setBackgroundResource(arrow_left)
                rightArrow!!.setBackgroundResource(arrow_right)
                cardNumberImage!!.setImageResource(ten)
            }

            12 -> {
                leftArrow!!.setBackgroundResource(arrow_left)
                rightArrow!!.setBackgroundResource(arrow_right)
                cardNumberImage!!.setImageResource(twelve)
            }

            16 -> {
                leftArrow!!.setBackgroundResource(arrow_left)
                rightArrow!!.setBackgroundResource(arrow_right)
                cardNumberImage!!.setImageResource(sixteen)
            }

            20 -> {
                leftArrow!!.setBackgroundResource(arrow_left)
                rightArrow!!.setBackgroundResource(arrow_right_transparency)
                cardNumberImage!!.setImageResource(twenty)
            }

            else -> {}
        }
    }

    fun initMenu(n: Int) {
        when (n) {
            4 -> {
                cardNumberImage!!.setImageResource(R.drawable.four_red)
                leftArrow!!.setBackgroundResource(R.drawable.arrow_left_red_transparency)
                rightArrow!!.setBackgroundResource(R.drawable.arrow_right_red)
            }

            6 -> {
                cardNumberImage!!.setImageResource(R.drawable.six_red)
                leftArrow!!.setBackgroundResource(R.drawable.arrow_left_red)
                rightArrow!!.setBackgroundResource(R.drawable.arrow_right_red)
            }

            8 -> {
                cardNumberImage!!.setImageResource(R.drawable.eight_red)
                leftArrow!!.setBackgroundResource(R.drawable.arrow_left_red)
                rightArrow!!.setBackgroundResource(R.drawable.arrow_right_red)
            }

            10 -> {
                cardNumberImage!!.setImageResource(R.drawable.ten_red)
                leftArrow!!.setBackgroundResource(R.drawable.arrow_left_red)
                rightArrow!!.setBackgroundResource(R.drawable.arrow_right_red)
            }

            12 -> {
                cardNumberImage!!.setImageResource(R.drawable.twelve_red)
                leftArrow!!.setBackgroundResource(R.drawable.arrow_left_red)
                rightArrow!!.setBackgroundResource(R.drawable.arrow_right_red)
            }

            16 -> {
                cardNumberImage!!.setImageResource(R.drawable.sixteen_red)
                leftArrow!!.setBackgroundResource(R.drawable.arrow_left_red)
                rightArrow!!.setBackgroundResource(R.drawable.arrow_right_red)
            }

            20 -> {
                cardNumberImage!!.setImageResource(R.drawable.twenty_red)
                leftArrow!!.setBackgroundResource(R.drawable.arrow_left_red)
                rightArrow!!.setBackgroundResource(R.drawable.arrow_right_red_transparency)
            }

            else -> {}
        }
    }
}
