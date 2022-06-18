package com.example.android.smartwg.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.smartwg.MainViewModel
import com.example.android.smartwg.R
import com.example.android.smartwg.ShoppingListInstanceActivity
import com.example.android.smartwg.model.ShoppingListItem
import kotlinx.android.synthetic.main.activity_shopping_list_instance.view.*
import kotlinx.android.synthetic.main.row_layout_shopping_list_items.view.*

class RecycAdapterShoppingListItems(
    val viewModel: MainViewModel,
    val shoppingListInstanceActivity: ShoppingListInstanceActivity,
) : RecyclerView.Adapter<RecycAdapterShoppingListItems.MyViewHolder>(){
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
        var itemAmount = shoppingListItemList[position].AMOUNT
        var itemTitle = shoppingListItemList[position].TITLE
        var itemUnit = shoppingListItemList[position].UNIT
        var itemID = shoppingListItemList[position].ID


        var cbCheckbox = holder.itemView.cbCheckBox
        var tvItemTitle = holder.itemView.tvItemTitle
        var tvItemAmount = holder.itemView.tvItemAmount
        var tvItemUnit  = holder.itemView.tvItemUnit
        var tvItemID = holder.itemView.tvItemID

        tvItemTitle.setText(itemTitle)
        tvItemAmount.setText(itemAmount.toString())
        tvItemUnit.setText(itemUnit)
        tvItemID.setText(itemID.toString())

        cbCheckbox.setOnClickListener {
            if (cbCheckbox.isChecked) {
                tvItemTitle.paintFlags = tvItemTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                holder.itemView.ShoppingListItemView.alpha = 0.6f
            } else {
                tvItemTitle.paintFlags = tvItemTitle.paintFlags and (Paint.STRIKE_THRU_TEXT_FLAG).inv()
                holder.itemView.ShoppingListItemView.alpha = 1.0f
            }
        }
    }

    override fun getItemCount(): Int {
        return shoppingListItemList.size
    }

    fun setData(shoppingListItemListIn: List<ShoppingListItem>) {
        shoppingListItemList = shoppingListItemListIn
        notifyDataSetChanged()
    }

    fun removeItem(position: Int) {
        viewModel.deleteShoppingListItem(shoppingListItemList[position].ID)
        notifyItemRemoved(position)
        shoppingListInstanceActivity.recreate()
    }

    fun getItem(position: Int): ShoppingListItem {
        return shoppingListItemList[position]
    }

    fun getData(): List<ShoppingListItem> {
        return shoppingListItemList
    }
}