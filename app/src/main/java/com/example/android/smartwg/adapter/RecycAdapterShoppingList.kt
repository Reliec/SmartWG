package com.example.android.smartwg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.smartwg.R
import com.example.android.smartwg.model.ShoppingList
import kotlinx.android.synthetic.main.row_layout_highscore.view.*
import kotlinx.android.synthetic.main.row_layout_shopping_list_boxes.view.*

class RecycAdapterShoppingList() : RecyclerView.Adapter<RecycAdapterShoppingList.MyViewHolder>(){
    private var myList:List<ShoppingList> = emptyList<ShoppingList>();

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout_shopping_list_boxes,parent,false))

    }

    override fun onBindViewHolder(holder: RecycAdapterShoppingList.MyViewHolder, position: Int) {
        holder.itemView.tvAuthor.text = myList?.get(position)?.AUTHOR.toString()
        holder.itemView.tvTitle.text = myList?.get(position)?.TITLE
        holder.itemView.tvLastEdited.text = myList?.get(position)?.LAST_EDITED
    }

    override fun getItemCount(): Int {
        return myList.size
    }
}