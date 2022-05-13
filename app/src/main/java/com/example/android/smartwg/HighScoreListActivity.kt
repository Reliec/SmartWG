package com.example.android.smartwg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_homescreen.*

class HighScoreListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score_list)

        val bKitchen = findViewById<Button>(R.id.bKitchen)
        bKitchen.setOnClickListener {
            val intent = Intent(this, HighScoreListKitchenActivity::class.java)
            startActivity(intent)
        }

        val bBathroom = findViewById<Button>(R.id.bBathroom)
        bBathroom.setOnClickListener {
            val intent = Intent(this, HighScoreListBathroomActivity::class.java)
            startActivity(intent)
        }

    }

}