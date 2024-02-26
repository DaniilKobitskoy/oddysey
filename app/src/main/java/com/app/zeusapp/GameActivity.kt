package com.app.zeusapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.collections.ArrayList

class GameActivity : AppCompatActivity() {

    private var MARGIN = 4
    private var ROWS: Int = 0
    private var COLUMNS: Int = 0
    private lateinit var mainlayout: LinearLayout
    private lateinit var pairs: TextView
    private val PAIRS = "Pairs: "
    private var layout_rows = ArrayList<LinearLayout>()
    private var cards = ArrayList<MemoryCard>()
    private var images = ArrayList<Int>()
    private var running = true
    private val SECONDS = 200
    private val END_SECONDS = 2000
    private lateinit var runnable: Runnable
    private lateinit var thread: Thread
    private var theme: Int = 0
    private var cardback: Int = 0
    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        val settings: SharedPreferences = getSharedPreferences("app_settings", Context.MODE_PRIVATE)
        theme = settings.getInt("THEME", R.style.AppTheme)
        setTheme(theme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        MemoryCard.deletecPairs()

        back = findViewById(R.id.back)
        back.setOnTouchListener { v, event ->
            val backIntent = Intent(this@GameActivity, FirstMenu::class.java)
            finish()
            startActivity(backIntent)
            true
        }

        ROWS = intent.getIntExtra("ROWS", 0)
        COLUMNS = intent.getIntExtra("COLUMS", 0)
        cardback = intent.getIntExtra("BACKSIDE", R.drawable.backside_red)

        images.addAll(listOf(
            R.drawable.z_1, R.drawable.z_2, R.drawable.z_3,
            R.drawable.z_4, R.drawable.z_5, R.drawable.z_6,
            R.drawable.z_7, R.drawable.z_8, R.drawable.z_9,
            R.drawable.z_10
        ))
        images.shuffle()


        mainlayout = findViewById(R.id.mainLayout)
        pairs = findViewById(R.id.pairs)
        pairs.text = PAIRS + "0"
        createCards(images, MARGIN, ROWS, COLUMNS, cardback)
        createLayoutRows(ROWS)
        addCardsToLayout(ROWS, COLUMNS, mainlayout)

        runnable = Runnable {
            while (running) {
                pairs.post {
                    pairs.text = PAIRS + MemoryCard.getcPairs()
                }
                try {
                    Thread.sleep(SECONDS.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }

                if (MemoryCard.getcPairs() == ROWS * COLUMNS / 2) {
                    pairs.post {
                        pairs.text = PAIRS + MemoryCard.getcPairs()
                    }
                    try {
                        Thread.sleep(END_SECONDS.toLong())
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    winGame()
                }
            }
        }

        thread = Thread(runnable)
        thread.start()
    }

    private fun winGame() {
        running = false
        thread.interrupt()
        val intent = Intent(this@GameActivity, WinningActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun restartGame() {
        running = false
        thread.interrupt()
        val intent = intent
        startActivity(intent)
        finish()
    }

    private fun addCardsToLayout(rows: Int, cols: Int, mLayout: LinearLayout) {
        var count = 0
        for (i in 0 until rows) {
            for (j in 0 until cols) {
                layout_rows[i].addView(cards[count].image)
                count++
            }
            mLayout.addView(layout_rows[i])
        }
    }

    private fun createLayoutRows(rows: Int) {
        for (i in 0 until rows) {
            val row = LinearLayout(this)
            row.orientation = LinearLayout.HORIZONTAL
            row.gravity = Gravity.CENTER_HORIZONTAL
            layout_rows.add(row)
        }
    }

    private fun createCards(images: ArrayList<Int>, margin: Int, rows: Int, cols: Int, cardback: Int) {
        val lp = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT)
        lp.setMargins(margin, margin, margin, margin)

        val storedImages = ArrayList<Any>()

        for (i in 0 until rows * cols / 2) {
            storedImages.add(images[i])
        }

        for (i in 0 until 2) {
            for (j in storedImages.indices) {
                val card: MemoryCard
                val imageResource = storedImages[j]
                if (imageResource is Int) {
                    card = MemoryCard(this, j + i * storedImages.size, j, imageResource, BitmapFactory.decodeResource(resources, cardback))
                } else if (imageResource is Int) {
                    card = MemoryCard(this, j + i * storedImages.size, j, imageResource, BitmapFactory.decodeResource(resources, cardback))
                } else {
                    throw IllegalArgumentException("Unsupported image type")
                }
                cards.add(card)
                cards[j + i * storedImages.size].image.layoutParams = lp
            }
        }

        cards.shuffle()
    }
}