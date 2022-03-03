package com.aridev.cordero.starwarsapp.data.retrofitRepository

import com.aridev.cordero.starwarsapp.core.RetrofitHelper
import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.data.ItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetrofitService {

    private val retrofit  = RetrofitHelper.getRetrofit()

    suspend fun getItemList( next : String ,callback : (success : ItemResponse? , error : String?) -> Unit) {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getItem(url = next)
            if(response.isSuccessful) {
                if( response.body() != null) {
                    callback.invoke(response.body(), null)
                } else {
                    callback.invoke(null,"Error App")
                }
            } else {
                callback.invoke(null,"Error App retrofit")
            }
        }
    }

    suspend fun getItemDetail( url : String, callback: (success: Item?, error: String?) -> Unit) {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).getItemDetail(url = url)
            if(response.isSuccessful) {
                if( response.body() != null) {
                    callback.invoke(response.body(), null)
                } else {
                    callback.invoke(null,"Error App")
                }
            } else {
                callback.invoke(null,"Error App retrofit")
            }
        }
    }


    suspend fun searchItem( url : String, callback: (success: ItemResponse?, error: String?) -> Unit) {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(ApiClient::class.java).searchItem(url = url)
            if(response.isSuccessful) {
                if( response.body() != null) {
                    callback.invoke(response.body(), null)
                } else {
                    callback.invoke(null,"Error App")
                }
            } else {
                callback.invoke(null,"Error App retrofit")
            }
        }
    }
}