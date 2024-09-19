package com.inik.mediasearch.repository

import com.inik.mediasearch.model.ListItem
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Query

interface SearchRepository {
    fun search(query: String): Observable<List<ListItem>>
}