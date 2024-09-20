package com.inik.shopping

import com.inik.shopping.model.ListItem
import com.inik.shopping.model.ViewType

data class Image(
    val imageUrl : String
): ListItem{
    override val viewType: ViewType
        get() = ViewType.IMAGE
}
