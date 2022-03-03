package com.aridev.cordero.starwarsapp.data.retrofitRepository

import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.data.ItemResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiClient {

    @GET()
    suspend fun getItem(@Url url: String): Response<ItemResponse>;
    @GET()
    suspend fun getItemDetail(@Url url : String) : Response<Item>

    @GET
    suspend fun searchItem(@Url url : String) : Response<ItemResponse>
}