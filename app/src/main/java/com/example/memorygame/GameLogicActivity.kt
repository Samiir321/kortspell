package com.example.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.media.Image
import android.media.MediaPlayer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class GameLogicActivity : AppCompatActivity() {
    var allPair = 0
    val doubleThePictures = mutableListOf<MemoryCard>()
    var firstSelectedImageView: ImageView? = null
    var secondeSelectedImageView: ImageView? = null
    val imageIds = listOf(
        R.id.imageButtonView0,
        R.id.imageButtonView1,
        R.id.imageButtonView2,
        R.id.imageButtonView3,
        R.id.imageButtonView4,
        R.id.imageButtonView5
    )
    lateinit var nameTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_logic)
        nameTextView = findViewById(R.id.playerNameTextView)
        var playerName = intent.getStringExtra("playerName")
        nameTextView.text = playerName
        val button = findViewById<Button>(R.id.BackButton)
        button.setOnClickListener {
            finish()
        }


        val memoryPictures = listOf(
            MemoryCard(R.drawable.memory_picture1, "card1", false),
            MemoryCard(R.drawable.memory_picture2, "card2", false),
            MemoryCard(R.drawable.memory_picture3, "card3", false)
        )

        for (image in memoryPictures) {
            repeat(2) {
                doubleThePictures.add(image)

            }
        }
        doubleThePictures.shuffle()

        for (id in imageIds) {
            val imageView = findViewById<ImageView>(id)

            imageView.setImageResource(R.drawable.memory_picture0)
            imageView.setOnClickListener {
                imageView.setImageResource(
                    doubleThePictures[imageView.tag.toString().toInt()].image
                )
                if (firstSelectedImageView == null) {
                    firstSelectedImageView = imageView
                } else {
                    secondeSelectedImageView = imageView
                    compareTheCard()
                }


            }


        }

    }

    fun resetGame() {

        doubleThePictures.shuffle()
        for (id in imageIds) {

            val imageView = findViewById<ImageView>(id)
            imageView.setImageResource(R.drawable.memory_picture0)

        }
        for (cards in doubleThePictures) {
            cards.isPair = false
        }
        allPair = 0
        firstSelectedImageView = null
        secondeSelectedImageView = null
    }

    fun winningTheGame() {
        allPair++
        if (allPair == 3) {
            val pairMusic = MediaPlayer.create(this, R.raw.wining_game_audio)
            pairMusic.start()
            Toast.makeText(this, "du vann", Toast.LENGTH_LONG).show()
            AlertDialog.Builder(this)
                .setTitle("Grattis")
                .setMessage("Du har hiitat alla par")
                .setPositiveButton("Spela igen") { dialog, which ->
                    resetGame()

                }
                .setNegativeButton("Avsluta") { dialog, which ->
                    finish()
                }.show()

        }
    }


    fun compareTheCard() {
        if (firstSelectedImageView != null && secondeSelectedImageView != null) {
            var firstCard = doubleThePictures[firstSelectedImageView!!.tag.toString().toInt()]
            var secondCard = doubleThePictures[secondeSelectedImageView!!.tag.toString().toInt()]
            if (firstCard.nameOfCard == secondCard.nameOfCard) {
                firstCard.isPair = true
                secondCard.isPair = true
                winningTheGame()


                val pairMusic = MediaPlayer.create(this, R.raw.is_pair_audio)
                pairMusic.start()
                firstSelectedImageView = null
                secondeSelectedImageView = null
            } else {
                Handler().postDelayed({
                    val pairMusic = MediaPlayer.create(this, R.raw.is_not_pair_audio)
                    pairMusic.start()
                    Toast.makeText(this, "Åh nej, det var inte ett par! ", Toast.LENGTH_LONG).show()
                    if (!firstCard.isPair) {
                        firstSelectedImageView?.setImageResource(R.drawable.memory_picture0)
                        firstSelectedImageView = null
                    }
                    if (!secondCard.isPair) {
                        secondeSelectedImageView?.setImageResource(R.drawable.memory_picture0)
                        secondeSelectedImageView = null
                    }
                }, 1000)

            }
        }

    }
}