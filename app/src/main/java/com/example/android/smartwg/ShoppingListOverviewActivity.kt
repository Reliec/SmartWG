package com.example.android.smartwg

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.android.smartwg.adapter.RecycAdapterHighscore
import com.example.android.smartwg.adapter.RecycAdapterShoppingList
import com.example.android.smartwg.repository.Repository
import kotlinx.android.synthetic.main.activity_high_score_list_bathroom.*

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

    }

}