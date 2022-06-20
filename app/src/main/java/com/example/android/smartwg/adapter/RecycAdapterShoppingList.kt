package com.example.android.smartwg.adapter

import android.content.Intent
import android.graphics.Typeface
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.android.smartwg.*
import com.example.android.smartwg.model.ShoppingList
import com.example.android.smartwg.repository.Repository
import com.example.myapplication.util.globals
import kotlinx.android.synthetic.main.row_layout_shopping_list_boxes.view.*

/**
 * Adapter for managing shopping lists in the recycler view
 * @param viewModelIn
 * @param parentActivityIn
 */
class RecycAdapterShoppingList(
    viewModelIn: MainViewModel,
    parentActivityIn: ShoppingListOverviewActivity
) : RecyclerView.Adapter<RecycAdapterShoppingList.MyViewHolder>(){
    private var viewModel = viewModelIn
    private var parentActivity = parentActivityIn
    private var shoppingListList:List<ShoppingList> = emptyList<ShoppingList>();

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    /**
     * Maps layout for shopping lists to the recycler view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout_shopping_list_boxes,parent,false))

    }

    /**
     * Maps buttons, symbols, etc. to a shopping list and implements their required logic
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: RecycAdapterShoppingList.MyViewHolder, position: Int) {
        val bEdit = holder.itemView.vEditShoppingList
        val bRemove = holder.itemView.vRemoveShoppingList
        val bConfirm = holder.itemView.vConfirmShoppingListChanges
        val tTitle = holder.itemView.tvTitle

        val typefaceItalic = Typeface.create(Typeface.SANS_SERIF, Typeface.ITALIC)
        val shoppingListID = shoppingListList[position].ID

        tTitle.setText(shoppingListList[position].TITLE)
        holder.itemView.tvAuthor.text = shoppingListList[position].FIRST_NAME
        holder.itemView.tvLastEdited.text = shoppingListList[position].LAST_EDITED

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ShoppingListInstanceActivity::class.java)
            intent.putExtra("Shopping List Title", tTitle.text.toString())
            intent.putExtra("Shopping List ID", shoppingListID)
            startActivity(holder.itemView.context,intent, null)
        }

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
            viewModel.updateShoppingListViewM(globals.userId, shoppingListID, holder.itemView.tvTitle.text)
            viewModel.echoStringResponse.observe(parentActivity) {
                parentActivity.getShoppingLists()
                notifyItemChanged(position)
            }
        }

        bRemove.setOnClickListener {
            viewModel.deleteShoppingListFromUserViewM(globals.userId, shoppingListID)
            viewModel.echoStringResponse.observe(parentActivity) {
                parentActivity.getShoppingLists()
                notifyItemRemoved(position)
            }
        }

    }

    /**
     * Returns the shopping list count
     * @return shopping list count
     */
    override fun getItemCount(): Int {
        return shoppingListList.size-1
    }

    /**
     * Sets the list containing the shopping lists list data and notifies the recycler view
     *
     * @param shoppingListListIn Data to be set
     */
    fun setData(shoppingListListIn : List<ShoppingList>) {
        shoppingListList = shoppingListListIn
        notifyDataSetChanged()
    }

    /**
     * Returns a list of shopping list
     *
     * @return current shoppingListList
     */
    fun getData(): List<ShoppingList> {
        return shoppingListList
    }
}