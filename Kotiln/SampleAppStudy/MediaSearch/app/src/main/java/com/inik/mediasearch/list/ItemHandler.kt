package com.inik.mediasearch.list

import com.inik.mediasearch.model.ListItem

interface ItemHandler {
    fun onClickFavorite(item : ListItem)
}