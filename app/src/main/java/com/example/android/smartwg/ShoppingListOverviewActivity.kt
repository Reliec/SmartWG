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
import kotlinx.android.synthetic.main.activity_shopping_list_overview.*

/**
 * Handles overview activity for shopping lists
 */
class ShoppingListOverviewActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private val recAdapterShoppingListOverview by lazy{
        RecycAdapterShoppingList(viewModel, this)
    }

    /**
     * Initialises the activity on creation.
     *
     * Maps Symbols, Buttons, etc. and fetches shopping Lists
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list_overview)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setupRecyclerViewShoppingList()
        getShoppingLists()


        val bCreateShoppingList = findViewById<Button>(R.id.bCreateNewShoppingList)

        bCreateShoppingList.setOnClickListener {
            viewModel.createShoppingListFromUserViewM(globals.userId)
            getShoppingLists()
            recAdapterShoppingListOverview.notifyItemInserted(recAdapterShoppingListOverview.getData().size-1)
        }
    }

    /**
     * Fetches shopping lists
     */
    fun getShoppingLists() {
        viewModel.getShoppingListsFromUserViewM(globals.userId)
        viewModel.shoppingListsResponse.observe(this
        ) { shoppingListResponse ->
            if (shoppingListResponse != null) {
                if (shoppingListResponse.isSuccessful) {
                    shoppingListResponse.body().let {
                        if (it != null) {
                            recAdapterShoppingListOverview.setData(it)
                            println(it)
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


    /**
     *Sets up the recycler view and its' adapter.
     */
    private fun setupRecyclerViewShoppingList() {
        recyclerViewShoppingListOverview.adapter = recAdapterShoppingListOverview
        recyclerViewShoppingListOverview.layoutManager = LinearLayoutManager(this)
        recyclerViewShoppingListOverview.setNestedScrollingEnabled(false)
    }

}