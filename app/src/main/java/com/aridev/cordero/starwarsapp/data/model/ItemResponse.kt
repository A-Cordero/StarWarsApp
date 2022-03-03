package com.aridev.cordero.starwarsapp.data

import java.io.Serializable

class ItemResponse(
    var results: List<Item>,
    var next : String
) : Serializable

class Item(
    var name: String?,
    var gender: String?,
    var title : String?,
    var height : String?,
    var mass : String?,
    var hair_color: String?,
    var skin_colors : String?,
    var eye_colors : String?,
    var hair_colors : String?,
    var eye_color : String ?,
    var birth_year : String?,
    var homeworld : String?,
    var films : List<String>?,
    var max_atmosphering_speed : String?,
    var species : List<String>?,
    var vehicles : List<String>?,
    var opening_crawl : String?,
    var director : String?,
    var producer : String?,
    var designation : String?,
    var release_date : String?,
    var characters : List<String>?,
    var planets : List<String>?,
    var starships : List<String>?,
    var classification : String?,
    var skin_color : String?,
    var language : String?,
    var people : List<String>?,
    var average_height : String?,
    var cost_in_credits : String?,
    var length : String,
    var manufacturer : String?,
    var vehicle_class : String?,
    var starship_class : String?,
    var model : String?,
    var rotation_period : String?,
    var diameter : String?,
    var climate : String?,
    var gravity : String?,
    var terrain : String?,
    var population : String?,
    var residents : List<String>?,
    var pilots : List<String>?,
    var url : String?
) : Serializable