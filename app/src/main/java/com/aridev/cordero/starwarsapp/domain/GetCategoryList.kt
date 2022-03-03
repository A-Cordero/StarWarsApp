package com.aridev.cordero.starwarsapp.domain

import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.data.model.Category
import javax.inject.Inject
import javax.inject.Singleton

class GetCategoryList @Inject constructor() {
    fun get(callback : (success : List<Category>) -> Unit) {
        val listCategories : List<Category> = listOf(
            Category(Categories.PEOPLE, true),
            Category(Categories.PLANETS, false),
            Category(Categories.FILMS, false),
            Category(Categories.SPECIES, false),
            Category(Categories.VEHICLES, false),
            Category(Categories.STARSHIPS, false)
        )
        callback.invoke(listCategories)
    }
}