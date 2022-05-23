package com.example.android.smartwg

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.smartwg.adapter.RecycAdapterShoppingList
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals
import kotlinx.android.synthetic.main.activity_high_score_list_kitchen.*
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
        getShoppingList(globals.userId)

        val bAddShoppingList = findViewById<Button>(R.id.bCreateNewShoppingList)
        bAddShoppingList.setOnClickListener {
            // TODO: Overlay-Form for creating a new Shopping List

            // createShoppingList(globals.userId)
        }

    }

    private fun getShoppingList(USERID: Int?) {
        viewModel.getShoppingListsFromUserViewM(globals.userId)
        viewModel.shoppingListResponse.observe(this
        ) { shoppingListResponse ->
            if (shoppingListResponse != null) {
                if (shoppingListResponse.isSuccessful) {
                    shoppingListResponse.body().let {
                        if (it != null) {
                            recAdapterShoppingListOverview.setData(it)
                            System.out.println(it)
                        }
                    }
                }
            } else {
                Toast.makeText(
                    this@ShoppingListOverviewActivity,
                    "Error: ShoppingListRepsonse is null!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

    private fun createShoppingList(USERID: Int?) {
        TODO("Not yet implemented")
    }

    private fun setupRecyclerViewShoppingList() {
        recyclerViewShoppingListOverview.adapter = recAdapterShoppingListOverview
        recyclerViewShoppingListOverview.layoutManager = LinearLayoutManager(this)
        recyclerViewShoppingListOverview.setNestedScrollingEnabled(false)
    }

}