package com.example.android.smartwg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals

/**
 * This Activity grants access to nearly all other Activities that the application offers. It can be seen
 * as a hub where the user has the choice to choose what to do.
 *
 */
class HomescreenActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homescreen)

        val tvWGName = findViewById<TextView>(R.id.tvWGName)
        tvWGName.text = globals.userWGName

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        var tvisToilet = findViewById<TextView>(R.id.tvWCStatus)

        System.out.println(globals.userSACode.toString() + " SACODE")
        System.out.println(globals.isToilet.toString() + " is Toilet")

        setToiletStatus(globals.userSACode)
        System.out.println(globals.isToilet.toString() + " is Toilet")

        if(globals.isToilet == 0){
            tvisToilet.text = "Toilet is free"
            tvisToilet.setBackgroundResource(R.drawable.toilet_free_background)
        }
        else{
            tvisToilet.text = "Toilet occupied"
            tvisToilet.setBackgroundResource(R.drawable.toilet_occupied_background)
        }

        System.out.println(globals.isToilet.toString() + " isToilet after set")

        tvisToilet.setOnClickListener{
            setToiletStatus(globals.userSACode)
            System.out.println(globals.isToilet.toString() + " is Toilet")
            if(globals.isToilet == 0){
                tvisToilet.text = "Toilet is free"
                tvisToilet.setBackgroundResource(R.drawable.toilet_free_background)
            }
            else{
                tvisToilet.text = "Toilet occupied"
                tvisToilet.setBackgroundResource(R.drawable.toilet_occupied_background)
            }
        }

        val bHighscoreList = findViewById<Button>(R.id.bHighscoreList)
        bHighscoreList.setOnClickListener {
            val intent = Intent(this, HighScoreListActivity::class.java)
            startActivity(intent)
        }

        val bShoppingList = findViewById<Button>(R.id.bShoppingList)
        bShoppingList.setOnClickListener {
            val intent = Intent(this, ShoppingListOverviewActivity::class.java)
            startActivity(intent)
        }

        val bSettings = findViewById<Button>(R.id.bSettings)
        bSettings.setOnClickListener{
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        val bWGBs = findViewById<Button>(R.id.bWGB)
        bWGBs.setOnClickListener{
            val intent = Intent(this, WGBsActivity::class.java)
            startActivity(intent)
        }

    }

    /**
     * Gets the current status of the isToulet value from database and stores it in globals.
     *
     * @param SACODE
     */
    private fun setToiletStatus(SACODE: Int?) {
        viewModel.getToiletStatusViewM(SACODE)
        viewModel.toiletStatusResponse.observe(
            this,
            Observer { response ->
                System.out.println(response.body()?.count())
                System.out.println(response.body())
                for(i in response.body()!!) {
                    System.out.println(i.isToilet.toString() + " response body")
                    globals.isToilet = i.isToilet
                }

            })
    }
}