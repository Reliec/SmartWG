package com.example.android.smartwg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.smartwg.R
import com.example.android.smartwg.model.Highscore
import com.example.android.smartwg.model.User
import kotlinx.android.synthetic.main.row_layout_highscore.view.*

class RecycAdapterHighscore() : RecyclerView.Adapter<RecycAdapterHighscore.MyViewHolder>(){
    private var myList:List<Highscore> = emptyList<Highscore>();

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycAdapterHighscore.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout_highscore,parent,false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: RecycAdapterHighscore.MyViewHolder, position: Int) {
        holder.itemView.tvName.text = myList?.get(position)?.NAME
        holder.itemView.tvFirstName.text = myList?.get(position)?.FIRST_NAME
        holder.itemView.tvDate.text = myList?.get(position)?.DATE
    }

    fun setData(newList : List<Highscore>){
        myList = newList
        notifyDataSetChanged()
    }
}