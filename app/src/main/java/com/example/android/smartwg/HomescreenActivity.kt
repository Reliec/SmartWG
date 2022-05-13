package com.example.android.smartwg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomescreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        val bHighscoreList = findViewById<Button>(R.id.bHighscoreList)
        bHighscoreList.setOnClickListener {
            val intent = Intent(this, HighScoreListActivity::class.java)
            startActivity(intent)
        }

    }

}