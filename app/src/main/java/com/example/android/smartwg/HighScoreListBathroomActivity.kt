package com.example.android.smartwg

import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.smartwg.adapter.RecycAdapterHighscore
import com.example.android.smartwg.model.Highscore
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals
import kotlinx.android.synthetic.main.activity_high_score_list_bathroom.*
import kotlinx.android.synthetic.main.activity_high_score_list_kitchen.*
import org.w3c.dom.Text
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

/**
 * Activity that shows the Highscores of the Bathroom. Users can create new Entrys
 * to show that they cleaned the room at a specific timestamp.
 *
 */
class HighScoreListBathroomActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private val recAdapterBathroom by lazy{
        RecycAdapterHighscore()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score_list_bathroom)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setupRecyclerViewHighscore()
        createHighscoreList(globals.userSACode, globals.userId, recAdapterBathroom)

        val bPlus = findViewById<Button>(R.id.bCreateNewBathroom)
        bPlus.setOnClickListener {
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.GERMAN)
            val currentDateAndTime: String =
                simpleDateFormat.format(Date())

            System.out.println(currentDateAndTime)

            insertNewHighscore(currentDateAndTime);
            Thread.sleep(1000)
            createHighscoreList(globals.userSACode, globals.userId, recAdapterBathroom)
        }
    }

    /**
     * Sets up Recycler View to set data that comes from backend.
     *
     */
    private fun setupRecyclerViewHighscore(){
        recyclerViewBathroom.adapter = recAdapterBathroom
        recyclerViewBathroom.layoutManager = LinearLayoutManager(this)
        recyclerViewBathroom.setNestedScrollingEnabled(false)
    }

    /**
     * Puts the data regarding highscores of users that cleand the bathroom into the RecyclerView.
     * Function gets the data from backend.
     *
     * @param SACODE
     * @param USERID
     * @param recAdapter
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun createHighscoreList(SACODE: Int?, USERID: Int?, recAdapter: RecycAdapterHighscore){
        viewModel.getHighscoresOfWGRepoViewM(SACODE, "Bathroom",USERID)
        viewModel.highscoreResponse.observe(this
        ) { highscoreListResponse ->
            if (highscoreListResponse != null) {
                if (highscoreListResponse.isSuccessful) {
                    highscoreListResponse.body().let {
                        if (it != null) {
                            recAdapter.setData(it)
                            //Log.d("TEST : ", it[0].toString())
                        }
                    }

                    var hashMap : HashMap<String, Int> = HashMap<String, Int> ()
                    for(item in highscoreListResponse.body()!!){
                        hashMap.merge(item.FIRST_NAME, 1,  { a, b -> a + b})
                    }

                    System.out.println(hashMap)

                    var layout = findViewById<RelativeLayout>(R.id.emptyLinearHighscoreBathroom);
                    layout.removeAllViews()
                    var prevTextViewId = 0
                    for (item in highscoreListResponse.body()!!) {
                        if(hashMap.contains(item.FIRST_NAME)) {
                            val textView = TextView(this)
                            textView.layoutParams = LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT
                            )
                            textView.setTextColor(Color.parseColor("#ffffff"))
                            textView.setText(item.FIRST_NAME + ": " + hashMap.get(item.FIRST_NAME))
                            var curTextViewId = prevTextViewId + 1
                            textView.id = curTextViewId
                            textView.textSize = 20.0f


                            var params = RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT
                            )
                            params.addRule(RelativeLayout.BELOW, prevTextViewId)
                            textView.layoutParams = params

                            prevTextViewId = curTextViewId
                            layout.addView(textView, params)
                            hashMap.remove(item.FIRST_NAME)
                        }
                    }
                }
            } else {
                if (highscoreListResponse != null) {
                    Toast.makeText(
                        this@HighScoreListBathroomActivity,
                        highscoreListResponse.code().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    /**
     * Creates a new Highscore and puts it into the database. The parmeter that corresponds to the
     * current time of the system.
     *
     * @param Date
     */
    private fun insertNewHighscore(Date: String) {
        viewModel.createNewHighscoreViewM(
            globals.userId,
            globals.userSACode,
            Date,
            "Bathroom",
            globals.userFirstName,
            globals.userLastName
        )
        viewModel.echoStringResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                var resp: String = response.body().toString()
                Toast.makeText(this, resp, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(
                    this,
                    response.code().toString() + " Create Highscore failed!",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

}