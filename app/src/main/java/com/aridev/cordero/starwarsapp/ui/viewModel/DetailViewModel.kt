package com.aridev.cordero.starwarsapp.ui.viewModel

import android.opengl.Visibility
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aridev.cordero.starwarsapp.core.dataApp.DetailState
import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.domain.GetItemDetail
import com.aridev.cordero.starwarsapp.domain.GetThemeApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor(
    private val getThemeApp: GetThemeApp,
    private val getITemDetail : GetItemDetail
): ViewModel()  {
    private var stateDetail : DetailState = DetailState.CLOSE
    private val _openCloseDetail = MutableLiveData<Int>()
    val openCloseDetail : LiveData<Int> = _openCloseDetail

    private val _themeState = MutableLiveData<String>()
    val themeState : LiveData<String> = _themeState

    private val _viewState = MutableLiveData<ViewState>()
    val viewState : LiveData<ViewState> = _viewState

    private val _listResidents = MutableLiveData<List<String>>()
    val listResidents : LiveData<List<String>> = _listResidents

    private val _listCharacters = MutableLiveData<List<String>>()
    val listCharacters : LiveData<List<String>> = _listCharacters

    private val _listSpecies = MutableLiveData<List<String>>()
    val listSpecies : LiveData<List<String>> = _listSpecies

    private val _listVehicles = MutableLiveData<List<String>>()
    val listVehicles : LiveData<List<String>> = _listVehicles

    private val _listStarships = MutableLiveData<List<String>>()
    val listStarships : LiveData<List<String>> = _listStarships

    private val _listPilots = MutableLiveData<List<String>>()
    val listPilots : LiveData<List<String>> = _listPilots

    private val _listPeople = MutableLiveData<List<String>>()
    val listPeople : LiveData<List<String>> = _listPeople

    private val _listPlanets = MutableLiveData<List<String>>()
    val listPlanets : LiveData<List<String>> = _listPlanets

    private val _listFilms = MutableLiveData<List<String>>()
    val listFilms : LiveData<List<String>> = _listFilms

    private val _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean> = _progress

    init {
        getTheme()
    }

    fun getTheme() {
        getThemeApp.invoke {
            _themeState.postValue( it)
        }
    }

    data class ViewState(
        var name : String = "",
        var mass : String = "",
        var imgDetail : String = "",
        var massView : Int = View.GONE,
        var openingCrawl : String = "",
        var openingCrawlView : Int = View.GONE,
        var director : String = "",
        var directorView : Int = View.GONE,
        var height : String = "",
        var heightView : Int = View.GONE,
        var productor : String = "",
        var productorView : Int = View.GONE,
        var releaseDate : String = "",
        var releaseDateView : Int = View.GONE,
        var model : String = "",
        var modelView : Int = View.GONE,
        var classification : String = "",
        var classificationView : Int = View.GONE,
        var rotation : String = "",
        var rotationView : Int = View.GONE,
        var cost : String = "",
        var costView : Int = View.GONE,
        var speed : String = "",
        var speedView : Int = View.GONE,
        var manufacturer : String = "",
        var manufacturerView : Int = View.GONE,
        var vehicleClass : String = "",
        var vehicleClassView : Int = View.GONE,
        var designation : String = "",
        var designationView : Int = View.GONE,
        var climate : String = "",
        var climateView : Int = View.GONE,
        var diameter : String = "",
        var diameterView : Int = View.GONE,
        var avgHeight : String = "",
        var homeWorldImg: String = "",
        var avgHeightView: Int = View.GONE,
        var language : String = "",
        var languageView : Int = View.GONE,
        var hairColor : String = "",
        var hairColorView : Int = View.GONE,
        var skinColor : String = "",
        var skinColorView : Int = View.GONE,
        var eyeColor : String = "",
        var eyeColorView : Int = View.GONE,
        var birth : String = "",
        var birthView : Int = View.GONE,
        var gender : String = "",
        var genderView : Int = View.GONE,
        var population : String = "",
        var populationView : Int = View.GONE,
        var homeWorld : String = "",
        var homeWorldView : Int = View.GONE,
        var residentsRvView : Int = View.GONE,
        var charactersRvView : Int = View.GONE,
        var speciesRvView : Int = View.GONE,
        var vehiclesRvView : Int = View.GONE,
        var starshipsRvView : Int = View.GONE,
        var pilotsRvView : Int = View.GONE,
        var peopleRvView : Int = View.GONE,
        var planetsRvView : Int = View.GONE,
        var filmsRvView : Int = View.GONE
    )

    fun getUi( category : String, item : String) {
        _progress.value = true
        viewModelScope.launch {
            getITemDetail.getItem(item) {success, error ->
                if(error.isNullOrEmpty()) {
                    when(category.lowercase()) {
                        Categories.PEOPLE.value.lowercase() -> setPeople(success!!)
                        Categories.PLANETS.value.lowercase() -> setPlanets(success!!)
                        Categories.FILMS.value.lowercase() -> setFilms(success!!)
                        Categories.SPECIES.value.lowercase() -> setSpecies(success!!)
                        Categories.VEHICLES.value.lowercase() -> setVehicle(success!!)
                        Categories.STARSHIPS.value.lowercase() -> setStarships(success!!)
                    }
                } else {
                    Log.d("App error : ", error)
                }
                _progress.postValue(false)
            }
        }
    }

    private fun setPeople(item : Item) {
        val viewState = ViewState(
            name = item.name ?: "",
            imgDetail = item.url ?: "",
            height = item.height ?: "",
            mass = item.mass ?: "",
            massView = View.VISIBLE,
            hairColor = item.hair_color ?: "",
            hairColorView = View.VISIBLE,
            skinColor = item.skin_color ?: "",
            skinColorView = View.VISIBLE ,
            eyeColor = item.eye_color ?: "",
            eyeColorView = View.VISIBLE,
            birth = item.birth_year ?: "",
            birthView = View.VISIBLE,
            gender = item.gender ?: "",
            genderView = View.VISIBLE,
            homeWorld = "Home World",
            homeWorldImg = item.homeworld ?: "",
            homeWorldView = View.VISIBLE,
            filmsRvView = View.VISIBLE,
            speciesRvView = View.VISIBLE,
            vehiclesRvView = View.VISIBLE,
            starshipsRvView = View.VISIBLE
        )
        _viewState.postValue(viewState)
        _listFilms.postValue(item.films ?: listOf())
        _listSpecies.postValue(item.species ?: listOf())
        _listVehicles.postValue(item.vehicles ?: listOf())
        _listStarships.postValue(item.starships ?: listOf())
    }

    private fun setPlanets(item : Item) {
        val viewState = ViewState(
            name = item.name ?: "",
            imgDetail = item.url ?: "",
            rotation = item.rotation_period ?: "",
            rotationView = View.VISIBLE,
            climate = item.climate ?: "",
            climateView = View.VISIBLE,
            diameter = item.diameter ?: "",
            diameterView = View.VISIBLE,
            population = item.population ?: "",
            populationView = View.VISIBLE,
            residentsRvView = View.VISIBLE,
            filmsRvView = View.VISIBLE
        )
        _viewState.postValue( viewState)
        _listResidents.postValue( item.residents ?: listOf())
        _listFilms.postValue( item.films ?: listOf())
    }

    private fun setFilms(item : Item) {
        val viewState = ViewState(
            name = item.title ?: "",
            imgDetail = item.url ?: "",
            openingCrawl = item.opening_crawl ?: "",
            openingCrawlView = View.VISIBLE,
            director = item.director ?: "",
            directorView = View.VISIBLE,
            productor = item.producer ?: "",
            productorView = View.VISIBLE,
            releaseDate = item.release_date ?: "",
            releaseDateView = View.VISIBLE,
            charactersRvView = View.VISIBLE,
            planetsRvView = View.VISIBLE,
            starshipsRvView = View.VISIBLE,
            vehiclesRvView = View.VISIBLE,
            speciesRvView = View.VISIBLE,
            )
        _viewState.postValue(viewState)
        _listCharacters.postValue(item.characters ?: listOf())
        _listPlanets.postValue(item.planets ?: listOf())
        _listStarships.postValue(item.starships ?: listOf())
        _listVehicles.postValue(item.vehicles ?: listOf())
        _listSpecies.postValue(item.species ?: listOf())

    }

    private fun setSpecies(item : Item) {
        val viewState = ViewState(
            name = item.name ?: "",
            imgDetail = item.url ?: "",
            classification = item.classification ?: "",
            classificationView = View.VISIBLE,
            designation = item.designation ?: "",
            designationView = View.VISIBLE,
            avgHeight = item.average_height ?: "",
            avgHeightView = View.VISIBLE,
            skinColor = item.skin_colors ?: "",
            hairColor = item.hair_colors ?: "",
            eyeColor = item.eye_colors ?: "",
            homeWorld = "Home World",
            homeWorldImg = item.homeworld ?: "",
            homeWorldView = View.VISIBLE,
            language = item.language ?: "",
            peopleRvView = View.VISIBLE,
            filmsRvView = View.VISIBLE
        )

        _viewState.postValue( viewState)
        _listPeople.postValue( item.people ?: listOf())
        _listFilms.postValue( item.films ?: listOf())
    }

    private fun setVehicle(item : Item) {
        val viewState = ViewState(
            name = item.name ?: "",
            imgDetail = item.url ?: "",
            model = item.model ?: "",
            modelView = View.VISIBLE,
            cost = item.cost_in_credits ?: "",
            costView = View.VISIBLE,
            vehicleClass = item.vehicle_class ?: "",
            vehicleClassView = View.VISIBLE,
            speed = item.max_atmosphering_speed ?: "",
            manufacturer = item.manufacturer ?: "",
            manufacturerView = View.VISIBLE,
            speedView = View.VISIBLE,
            pilotsRvView = View.VISIBLE,
            filmsRvView = View.VISIBLE
        )
        _viewState.postValue( viewState)
        _listPilots.postValue( item.pilots ?: listOf())
        _listFilms.postValue( item.films ?: listOf())
    }

    private fun setStarships(item : Item) {
        val viewState = ViewState(
            name = item.name ?: "",
            imgDetail = item.url ?: "",
            model = item.model ?: "",
            modelView = View.VISIBLE,
            cost = item.cost_in_credits ?: "",
            costView = View.VISIBLE,
            vehicleClass = item.starship_class ?: "",
            vehicleClassView = View.VISIBLE,
            speed = item.max_atmosphering_speed ?: "",
            manufacturer = item.manufacturer ?: "",
            manufacturerView = View.VISIBLE,
            speedView = View.VISIBLE,
            pilotsRvView = View.VISIBLE,
            filmsRvView = View.VISIBLE
        )
        _viewState.postValue( viewState)
        _listPilots.postValue( item.pilots ?: listOf())
        _listFilms.postValue( item.films ?: listOf())
    }

    fun changeStateDetail( openHeight : Int, closeHeight : Int) {
        if( stateDetail == DetailState.CLOSE) {
            _openCloseDetail.value = (openHeight - closeHeight)
            stateDetail = DetailState.OPEN
        } else {
            _openCloseDetail.value = -1*(openHeight - closeHeight)
            stateDetail = DetailState.CLOSE
        }
    }

}
