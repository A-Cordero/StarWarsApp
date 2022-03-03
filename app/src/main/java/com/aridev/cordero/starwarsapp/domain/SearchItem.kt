package com.aridev.cordero.starwarsapp.domain

import com.aridev.cordero.starwarsapp.data.ItemResponse
import com.aridev.cordero.starwarsapp.data.retrofitRepository.RetrofitService
import javax.inject.Inject

class SearchItem  @Inject constructor() {
    private val api = RetrofitService()

    suspend fun getSearch(url : String,callback: (success: ItemResponse?, error: String?) -> Unit) {
        api.searchItem(url) { success, error ->
            if (error.isNullOrEmpty()) {
                callback.invoke( success, null)
            } else {
                callback.invoke(null, error)
            }
        }
    }
}