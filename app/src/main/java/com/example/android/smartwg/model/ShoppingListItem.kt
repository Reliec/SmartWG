package com.example.android.smartwg.model

import android.text.Editable

data class ShoppingListItem(
    val ID: Int,
    val title: Editable,
    val count: Int,
    val shoppingList: Int
)
