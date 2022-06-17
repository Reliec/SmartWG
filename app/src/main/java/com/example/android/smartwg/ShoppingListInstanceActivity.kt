package com.example.android.smartwg

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.smartwg.adapter.RecycAdapterShoppingListItems
import com.example.android.smartwg.repository.Repository
import kotlinx.android.synthetic.main.activity_shopping_list_instance.*
import kotlinx.android.synthetic.main.activity_shopping_list_overview.*

class ShoppingListInstanceActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private val recAdapterShoppingListItems by lazy{
        RecycAdapterShoppingListItems(viewModel, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list_instance)
        val intent = intent


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        setupRecyclerViewShoppingListItems()

        val tvShoppingListTitle = findViewById<TextView>(R.id.tvShoppingListTitle)
        tvShoppingListTitle.text = intent.getStringExtra("Shopping List Title")


        getShoppingListItems()


    }

    fun getShoppingListItems() {

        /**
        viewModel.getShoppingListsFromUserViewM(globals.userId)
        viewModel.shoppingListResponse.observe(this
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
        */
    }

    private fun setupRecyclerViewShoppingListItems() {
        // TODO IMPLEMENT RECYLCER
        recyclerViewShoppingListItems.adapter = recAdapterShoppingListItems
        recyclerViewShoppingListItems.layoutManager = LinearLayoutManager(this)
        recyclerViewShoppingListItems.setNestedScrollingEnabled(false)
    }

}