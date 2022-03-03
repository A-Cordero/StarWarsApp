package com.aridev.cordero.starwarsapp.domain

import com.aridev.cordero.starwarsapp.data.ItemResponse
import com.aridev.cordero.starwarsapp.data.retrofitRepository.RetrofitService
import javax.inject.Inject

class GetItemList @Inject constructor()  {

    private val api = RetrofitService()

    suspend fun getItemList(next : String,callback: (success: ItemResponse?, error: String?) -> Unit) {
        api.getItemList(next) { success, error ->
            if (error.isNullOrEmpty()) {
                callback.invoke( success, null)
            } else {
                callback.invoke(null, error)
            }
        }
    }
}