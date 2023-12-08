package com.example.memorygame

import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    val MenyFragment = MenyFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addMeny()


    }

    fun addMeny() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.container, MenyFragment, "MenyFragment")
        transaction.commit()
    }
}