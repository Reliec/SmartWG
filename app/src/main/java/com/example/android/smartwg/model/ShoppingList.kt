package com.example.android.smartwg.model

data class ShoppingList(
    val ID: Int,
    val TITLE: Int,
    val LAST_EDITED: Int,
    val Author: User,
    val PARTICIPANTS: List<User>
)