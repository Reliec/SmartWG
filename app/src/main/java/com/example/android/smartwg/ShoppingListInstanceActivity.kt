package com.example.android.smartwg

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.forEach
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.smartwg.adapter.RecycAdapterShoppingListItems
import com.example.android.smartwg.repository.Repository
import com.example.android.smartwg.util.SwipeToDeleteCallback
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_shopping_list_instance.*
import kotlinx.android.synthetic.main.row_layout_shopping_list_boxes.*


class ShoppingListInstanceActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    // private lateinit var recyclerViewShoppingListItems: RecyclerView
    lateinit var constraintLayout: ConstraintLayout


    private val recAdapterShoppingListItems by lazy{
        RecycAdapterShoppingListItems(viewModel, this)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list_instance)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        constraintLayout = findViewById(R.id.constraintLayoutShoppingListInstance);
        setupRecyclerViewShoppingListItems()

        val typefaceItalic = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
        var vEditShoppingListItems = findViewById<ImageView>(R.id.vEditShoppingListItems)
        var vConfirmShoppingListItemChanges = findViewById<ImageView>(R.id.vConfirmShoppingListItemChanges)
        val tvShoppingListTitle = findViewById<TextView>(R.id.tvShoppingListTitle)
        val bCreateShoppingListitem = findViewById<Button>(R.id.bCreateShoppingListItem)
        val shoppingListID = intent.getIntExtra("Shopping List ID", 0)
        tvShoppingListTitle.text = intent.getStringExtra("Shopping List Title")

        getShoppingListItems()
        enableSwipeToDelete()

        vEditShoppingListItems.setOnClickListener {
            recyclerViewShoppingListItems.forEach {
                var tvItemTitle = it.findViewById<EditText>(R.id.tvItemTitle)
                var tvItemUnit = it.findViewById<EditText>(R.id.tvItemUnit)
                var tvItemAmount = it.findViewById<EditText>(R.id.tvItemAmount)

                tvItemTitle.typeface = typefaceItalic
                tvItemUnit.typeface = typefaceItalic
                tvItemAmount.typeface = typefaceItalic

                tvItemTitle.inputType = InputType.TYPE_CLASS_TEXT
                tvItemAmount.inputType = InputType.TYPE_CLASS_TEXT
                tvItemUnit.inputType = InputType.TYPE_CLASS_TEXT

                vEditShoppingListItems.visibility = View.INVISIBLE
                vConfirmShoppingListItemChanges.visibility = View.VISIBLE
            }
        }

        vConfirmShoppingListItemChanges.setOnClickListener {
            recyclerViewShoppingListItems.forEach {
                var tvItemTitle = it.findViewById<EditText>(R.id.tvItemTitle)
                var tvItemUnit = it.findViewById<EditText>(R.id.tvItemUnit)
                var tvItemAmount = it.findViewById<EditText>(R.id.tvItemAmount)
                var tvItemID = it.findViewById<TextView>(R.id.tvItemID)
                tvItemTitle.typeface = Typeface.SANS_SERIF
                tvItemUnit.typeface = Typeface.SANS_SERIF
                tvItemAmount.typeface = Typeface.SANS_SERIF

                tvItemTitle.inputType = InputType.TYPE_NULL
                tvItemAmount.inputType = InputType.TYPE_NULL
                tvItemUnit.inputType = InputType.TYPE_NULL

                vEditShoppingListItems.visibility = View.VISIBLE
                vConfirmShoppingListItemChanges.visibility = View.INVISIBLE


                var id = tvItemID.text.toString().toInt()
                var newTitle = tvItemTitle.text.toString()
                var newAmount = tvItemAmount.text.toString()
                var newUnit = tvItemUnit.text.toString()
                viewModel.updateShoppingListItemViewModel(id, newTitle, newAmount, newUnit)
            }
        }

        bCreateShoppingListitem.setOnClickListener {
            viewModel.createShoppingListItem(shoppingListID)
            this.recreate()
        }
    }

    private fun enableSwipeToDelete() {
        val swipeToDeleteCallback: SwipeToDeleteCallback = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
                val position: Int = viewHolder.adapterPosition
                recAdapterShoppingListItems.removeItem(position)
                val snackbar: Snackbar = Snackbar
                    .make(
                        constraintLayout,
                        "Item was removed from the list.",
                        Snackbar.LENGTH_LONG
                    )
                snackbar.setActionTextColor(Color.YELLOW)
                snackbar.show()
            }
        }
        val itemTouchhelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchhelper.attachToRecyclerView(recyclerViewShoppingListItems)
    }

    fun getShoppingListItems() {
        viewModel.getShoppingListItemsViewM(intent.getIntExtra("Shopping List ID", 0))
        viewModel.shoppingListItemsResponse.observe(this
        ) { shoppingListItemsResponse ->
            if (shoppingListItemsResponse != null) {
                if (shoppingListItemsResponse.isSuccessful) {
                    shoppingListItemsResponse.body().let {
                        if (it != null) {
                            recAdapterShoppingListItems.setData(it)
                            println(it)
                        }
                    }
                }
            } else {
                Toast.makeText(
                    this@ShoppingListInstanceActivity,
                    "Error: ShoppingListRepsonse is null!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun setupRecyclerViewShoppingListItems() {
        // recyclerViewShoppingListItems = RecyclerView(this)
        recyclerViewShoppingListItems.adapter = recAdapterShoppingListItems
        recyclerViewShoppingListItems.layoutManager = LinearLayoutManager(this)
        recyclerViewShoppingListItems.isNestedScrollingEnabled = false
    }

}