package com.inik.shopping.model

import android.view.View
import java.io.Serializable

interface ListItem: Serializable{
    val viewType : ViewType

    fun getKey() = hashCode()
}

enum class ViewType {
    VIEW_PAGER,
    HORIZONTAL,
    FULL_AD,

    SELL_ITEM,
    IMAGE,
    SALE,
    COUPON,

    EMPTY
}