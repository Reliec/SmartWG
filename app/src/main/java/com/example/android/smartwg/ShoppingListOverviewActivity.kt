package com.example.android.smartwg

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.adapter.RecycAdapterShoppingList
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals
import kotlinx.android.synthetic.main.activity_high_score_list_bathroom.*
import kotlinx.android.synthetic.main.activity_shopping_list_overview.*

class ShoppingListOverviewActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private val recAdapterShoppingListOverview by lazy{
        RecycAdapterShoppingList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list_overview)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setupRecyclerViewShoppingList()
        viewModel.getShoppingListsFromUser(globals.userId)

        val bAddShoppingList = findViewById<Button>(R.id.bCreateNewShoppingList)
        bAddShoppingList.setOnClickListener {
            // TODO: Overlay-Form for creating a new Shopping List

            createShoppingList(globals.userId)
        }

    }

    private fun createShoppingList(USERID: Int?) {
        TODO("Not yet implemented")
    }

    private fun setupRecyclerViewShoppingList() {
        recyclerViewShoppingListOverview.adapter = recAdapterShoppingListOverview
    }

}