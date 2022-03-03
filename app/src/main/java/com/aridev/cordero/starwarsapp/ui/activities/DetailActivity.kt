package com.aridev.cordero.starwarsapp.ui.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.core.dataApp.getDrawableImage
import com.aridev.cordero.starwarsapp.core.dataApp.getHeightCustom
import com.aridev.cordero.starwarsapp.core.dataApp.increaseViewHeightAnimation
import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.databinding.ActivityDetailBinding
import com.aridev.cordero.starwarsapp.ui.adapter.SubItemAdapter
import com.aridev.cordero.starwarsapp.ui.dialog.ProgressDialog
import com.aridev.cordero.starwarsapp.ui.viewModel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val viewModel : DetailViewModel by viewModels()
    private lateinit var binding : ActivityDetailBinding

    lateinit var progress : ProgressDialog
    lateinit var category : String
    lateinit var item : String

    private val adapterResidents = SubItemAdapter()
    private val adapterSpecies = SubItemAdapter()
    private val adapterStarships = SubItemAdapter()
    private val adapterPilots = SubItemAdapter()
    private val adapterPeople = SubItemAdapter()
    private val adapterPlanets = SubItemAdapter()
    private val adapterFilms = SubItemAdapter()
    private val adapterCharacters = SubItemAdapter()
    private val adapterVehicles = SubItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        category = intent?.getStringExtra("category") ?: ""
        item = intent?.getStringExtra("item") ?: ""
        progress = ProgressDialog(this)
        setObservers()
        setListeners()
    }

    private fun setListeners() {
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnExpand.setOnClickListener {
            viewModel.changeStateDetail(binding.containerHeightXpand.getHeightCustom(),binding.containerDetailTop.getHeightCustom())
        }
    }

    private fun setObservers() {
        viewModel.apply {
            progress.observe(this@DetailActivity) {
                this@DetailActivity.progress.setProgress(it)
            }
            themeState.observe(this@DetailActivity) {
                when (it) {
                    ThemeApp.LIGHT.value -> {
                        this@DetailActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                    }

                    ThemeApp.DARK.value -> {
                        this@DetailActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
                    }

                    ThemeApp.OS.value -> {
                        this@DetailActivity.delegate.localNightMode =
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                }
            }

            openCloseDetail.observe(this@DetailActivity) {
                binding.containerDetail.increaseViewHeightAnimation(it) {}
            }

            viewState.observe(this@DetailActivity){
                render(it)
            }
            listResidents.observe(this@DetailActivity) {
                setAdapterResidents(it)
            }

            listSpecies.observe(this@DetailActivity) {
                setAdapterSpecies(it)
            }

            listStarships.observe(this@DetailActivity) {
                setAdapterStarships(it)
            }

            listPilots.observe(this@DetailActivity) {
                setAdapterPilots(it)
            }

            listPeople.observe(this@DetailActivity) {
                setAdapterPeople(it)
            }

            listPlanets.observe(this@DetailActivity) {
                setAdapterPlanets(it)
            }

            listFilms.observe(this@DetailActivity) {
                setAdapterFilms(it)
            }

            listVehicles.observe(this@DetailActivity) {
                setAdapterVehicles(it)
            }

            listCharacters.observe(this@DetailActivity) {
                setAdapterCharacteres(it)
            }
            getUi(category, item)
        }
    }

    private fun setAdapterCharacteres(it: List<String>) {
        adapterCharacters.list = it
        binding.rvCharacters.adapter = adapterCharacters
        adapterCharacters.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.PEOPLE.value)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapterFilms(it: List<String>) {
        adapterFilms.list = it
        binding.rvFilms.adapter = adapterFilms
        adapterFilms.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.FILMS.value)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapterVehicles(it : List<String>) {
        adapterVehicles.list = it
        binding.rvVehicles.adapter = adapterVehicles
        adapterVehicles.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.VEHICLES.value)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapterPlanets(it: List<String>) {
        adapterPlanets.list = it
        binding.rvPlanets.adapter = adapterPlanets
        adapterPlanets.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.PLANETS.value)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapterPeople(it: List<String>) {
        adapterPeople.list = it
        binding.rvPeople.adapter = adapterPeople
        adapterPeople.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.PEOPLE.value)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapterPilots(it: List<String>) {
        adapterPilots.list = it
        binding.rvPilots.adapter = adapterPilots
        adapterPilots.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.PEOPLE.value)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapterStarships(it: List<String>) {
        adapterStarships.list = it
        binding.rvStarships.adapter = adapterStarships
        adapterStarships.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.STARSHIPS.value)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapterSpecies(it: List<String>) {
        adapterSpecies.list = it
        binding.rvSpecies.adapter = adapterSpecies
        adapterSpecies.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.SPECIES.value)
            startActivity(intent)
            finish()
        }
    }

    private fun setAdapterResidents(it: List<String>) {
        adapterResidents.list = it
        binding.rvResidents.adapter = adapterResidents
        adapterResidents.actionDetail = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("item", it)
            intent.putExtra("category",Categories.PEOPLE.value)
            startActivity(intent)
            finish()
        }
    }

    private fun render(state: DetailViewModel.ViewState) {
        binding.apply {
            imgDetail.setImageResource(this@DetailActivity.getDrawableImage(state.imgDetail))
            textName.text = state.name.lowercase()
            containerMass.visibility = state.massView
            textMass.text = state.mass
            containerOpening.visibility = state.openingCrawlView
            textOpening.text = state.openingCrawl
            containerDirector.visibility = state.directorView
            textDirector.text = state.director
            containerProductor.visibility = state.directorView
            textProductor.text = state.director
            containerRelease.visibility = state.releaseDateView
            textRelease.text = state.releaseDate
            containerModel.visibility = state.modelView
            textModel.text = state.model
            containerClassification.visibility = state.classificationView
            textClassification.text = state.classification
            containerHeight.visibility = state.heightView
            textHeight.text = state.height
            containerSpeed.visibility = state.speedView
            textSpeed.text  = state.speed
            containerManufacturer.visibility = state.manufacturerView
            textManufacturer.text = state.manufacturer
            containerCost.visibility = state.costView
            textCost.text = state.cost
            containerVehicle.visibility = state.vehicleClassView
            textVehicle.text = state.vehicleClass
            containerDesignation.visibility = state.designationView
            textDesignation.text = state.designation
            containerAvgHeight.visibility = state.avgHeightView
            textAvgHeight.text = state.avgHeight
            containerLanguage.visibility = state.languageView
            textLanguage.text = state.language
            containerHairColor.visibility = state.hairColorView
            textHairColor.text = state.hairColor
            containerSkinColor.visibility = state.skinColorView
            textSkinColor.text = state.skinColor
            containerEyeColor.visibility = state.eyeColorView
            textEyeColor.text = state.eyeColor
            containerBirth.visibility = state.birthView
            textBirth.text = state.birth
            containerGender.visibility = state.genderView
            textGender.text = state.gender
            containerRotation.visibility = state.rotationView
            textRotation.text = state.rotation
            containerDiameter.visibility = state.diameterView
            textDiameter.text = state.diameter
            containerClimate.visibility = state.climateView
            textClimate.text = state.climate
            containerPopulation.visibility = state.populationView
            textPopulation.text = state.population
            containerResidents.visibility = state.residentsRvView
            containerSpecies.visibility = state.speciesRvView
            containerStarships.visibility = state.starshipsRvView
            containerPilots.visibility = state.pilotsRvView
            containerPeople.visibility = state.peopleRvView
            containerPlanets.visibility = state.planetsRvView
            containerFilms.visibility = state.filmsRvView
            containerCharacters.visibility= state.charactersRvView
        }
    }
}