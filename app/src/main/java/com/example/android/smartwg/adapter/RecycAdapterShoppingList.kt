package com.example.android.smartwg.adapter

import android.graphics.Typeface
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.android.smartwg.MainViewModel
import com.example.android.smartwg.MainViewModelFactory
import com.example.android.smartwg.R
import com.example.android.smartwg.model.ShoppingList
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals
import kotlinx.android.synthetic.main.row_layout_shopping_list_boxes.view.*

class RecycAdapterShoppingList(viewModelIn: MainViewModel) : RecyclerView.Adapter<RecycAdapterShoppingList.MyViewHolder>(){
    var viewModel = viewModelIn
    private var shoppingListList:List<ShoppingList> = emptyList<ShoppingList>();

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout_shopping_list_boxes,parent,false))

    }

    override fun onBindViewHolder(holder: RecycAdapterShoppingList.MyViewHolder, position: Int) {
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        // viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val bEdit = holder.itemView.vEditShoppingList
        val bRemove = holder.itemView.vRemoveShoppingList
        val bConfirm = holder.itemView.vConfirmShoppingListChanges
        val tTitle = holder.itemView.tvTitle

        val typefaceItalic = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
        val shoppingListID = shoppingListList[position].ID

        tTitle.setText(shoppingListList[position].TITLE)
        holder.itemView.tvAuthor.text = shoppingListList[position].FIRST_NAME
        holder.itemView.tvLastEdited.text = shoppingListList[position].LAST_EDITED


        bEdit.setOnClickListener {
            bEdit.visibility = View.INVISIBLE
            bConfirm.visibility = View.VISIBLE
            bRemove.visibility = View.VISIBLE
            tTitle.inputType = InputType.TYPE_CLASS_TEXT
            tTitle.typeface = typefaceItalic
        }

        bConfirm.setOnClickListener {
            bConfirm.visibility = View.INVISIBLE
            bEdit.visibility = View.VISIBLE
            bRemove.visibility = View.INVISIBLE
            tTitle.inputType = InputType.TYPE_NULL
            tTitle.typeface = Typeface.SANS_SERIF
        }

        bRemove.setOnClickListener {
            viewModel.deleteShoppingListFromUserViewM(globals.userId, shoppingListID)
            notifyItemRemoved(position)
        }

    }

    override fun getItemCount(): Int {
        return shoppingListList.size
    }

    fun setData(shoppingListListIn : List<ShoppingList>) {
        shoppingListList = shoppingListListIn
        notifyDataSetChanged()
    }
}