package com.example.android.smartwg.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.smartwg.model.User
import com.example.android.smartwg.R
import kotlinx.android.synthetic.main.activity_main.view.*


class RecycAdapterUser(): RecyclerView.Adapter<RecycAdapterUser.MyViewHolder>() {
    private var myList:List<User> = emptyList<User>();

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_main,parent,false))
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tvTest.text = myList?.get(position)?.NAME
    }

    fun setData(newList : List<User>){
        myList = newList
        notifyDataSetChanged()
    }
}