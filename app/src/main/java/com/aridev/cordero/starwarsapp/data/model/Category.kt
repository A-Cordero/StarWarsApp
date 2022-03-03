package com.aridev.cordero.starwarsapp.data.model

import com.aridev.cordero.starwarsapp.R
import java.io.Serializable

class Category  (
    var type: Categories,
    var status: Boolean
) : Serializable

enum class Categories(var value: String, var icon: Int, var url : String) : Serializable{
    HOME("Home",R.drawable.home_ic,""),
    SEARCH("Search",R.drawable.seach_ic,""),
    PEOPLE("People", R.drawable.people,"https://swapi.py4e.com/api/people/"),
    PLANETS("Planets", R.drawable.planet,"https://swapi.py4e.com/api/planets/"),
    FILMS("Films", R.drawable.lightsaber,"https://swapi.py4e.com/api/films/"),
    SPECIES("Species", R.drawable.species,"https://swapi.py4e.com/api/species/"),
    VEHICLES("Vehicles", R.drawable.vehicle,"https://swapi.py4e.com/api/vehicles/"),
    STARSHIPS("Starships", R.drawable.starships,"https://swapi.py4e.com/api/starships/")
}