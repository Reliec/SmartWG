package com.example.android.smartwg.model

import android.text.Editable

data class ShoppingListItem(
    val ID: Int,
    val title: Editable,
    val amount: Int,
    val unit: String,
    val shoppingList: Int)
