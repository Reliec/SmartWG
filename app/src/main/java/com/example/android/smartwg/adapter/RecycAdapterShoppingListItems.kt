package com.example.android.smartwg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.smartwg.MainViewModel
import com.example.android.smartwg.R
import com.example.android.smartwg.ShoppingListInstanceActivity
import com.example.android.smartwg.model.ShoppingListItem
import kotlinx.android.synthetic.main.row_layout_shopping_list_items.view.*
import okhttp3.internal.notify

class RecycAdapterShoppingListItems(
    viewModel: MainViewModel,
    shoppingListInstanceActivity: ShoppingListInstanceActivity
) : RecyclerView.Adapter<RecycAdapterShoppingListItems.MyViewHolder>() {
    private var shoppingListItemList:List<ShoppingListItem> = emptyList<ShoppingListItem>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecycAdapterShoppingListItems.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout_shopping_list_items,parent,false))
    }

    override fun onBindViewHolder(
        holder: RecycAdapterShoppingListItems.MyViewHolder,
        position: Int
    ) {


    }

    override fun getItemCount(): Int {
        return shoppingListItemList.size
    }

    fun setData(shoppingListItemListIn: List<ShoppingListItem>) {
        shoppingListItemList = shoppingListItemListIn
        notifyDataSetChanged()
    }
}