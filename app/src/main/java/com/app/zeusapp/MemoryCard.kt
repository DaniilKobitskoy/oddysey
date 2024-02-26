package com.app.zeusapp

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.animation.ObjectAnimator

class MemoryCard(context: Context, private val id: Int, private val nr: Int, cardFront: Int, cardBack: Bitmap) : View.OnClickListener {

    private val PARAMS = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    private val cardBack: Bitmap = cardBack
    private val cardFront: Bitmap = android.graphics.BitmapFactory.decodeResource(context.resources, cardFront)
    val image: ImageView = ImageView(context)
    private var clicked = false

    companion object {
        private const val SECONDS = 500
        private var cPairs = 0
        private val clickedCards = ArrayList<MemoryCard>()

        fun getcPairs(): Int {
            return cPairs
        }

        fun deletecPairs() {
            cPairs = 0
        }
    }

    init {
        image.layoutParams = PARAMS
        image.setImageBitmap(this.cardBack)
        image.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (!clicked && clickedCards.size < 2) {
            flipCardtoFront()
            clickedCards.add(this)

            if (clickedCards.size == 2) {
                val handler = Handler()
                handler.postDelayed({
                    if (clickedCards[0].getNumber() == clickedCards[1].getNumber()) {
                        ++cPairs
                    } else {
                        clickedCards[0].flipCardtoBack()
                        clickedCards[1].flipCardtoBack()
                    }
                    clickedCards.clear()
                }, SECONDS.toLong())
            }
        }
    }

    private fun flipCardtoFront() {
        val flip = ObjectAnimator.ofFloat(image, "rotationY", 180f, 0f)
        image.setImageBitmap(cardFront)
        clicked = true
    }

    private fun flipCardtoBack() {
        image.setImageBitmap(cardBack)
        clicked = false
    }



    fun getNumber(): Int {
        return nr
    }
}
