package com.aridev.cordero.starwarsapp.domain

import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.data.ItemResponse
import com.aridev.cordero.starwarsapp.data.retrofitRepository.RetrofitService
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

class GetItemDetail @Inject constructor() {
    private val api = RetrofitService()

    suspend fun getItem(next : String,callback: (success: Item?, error: String?) -> Unit) {
        api.getItemDetail(next) { success, error ->
            if (error.isNullOrEmpty()) {
                callback.invoke( success, null)
            } else {
                callback.invoke(null, error)
            }
        }
    }
}