package com.example.android.smartwg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.myapplication.util.globals

/**
 * Simple activity that shows the overall rules of a sharedappartment.
 *
 */
class WGBsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wgbs)

        val tvWGBs = findViewById<TextView>(R.id.tvWGBSActivity)
        tvWGBs.text = globals.userWGBs

    }
}