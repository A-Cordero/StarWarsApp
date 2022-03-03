package com.aridev.cordero.starwarsapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.core.dataApp.increaseViewWidthAnimation
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.databinding.ActivityMainBinding
import com.aridev.cordero.starwarsapp.ui.fragments.CategoriesFragment
import com.aridev.cordero.starwarsapp.ui.fragments.SettingsFragment
import android.content.Context
import android.content.res.Configuration

import android.util.TypedValue
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.core.dataApp.setOnClickListenerBounce
import com.aridev.cordero.starwarsapp.ui.fragments.HomeFragment
import com.aridev.cordero.starwarsapp.ui.fragments.SearchFragment
import com.aridev.cordero.starwarsapp.ui.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var settingFragment = SettingsFragment()
    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    lateinit var actionChangeCategory: ((category: Categories) -> Unit)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUi()
        setListeners()
        setDrawerListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.apply {
            themeState.observe(this@MainActivity) {
                when (it) {
                    ThemeApp.LIGHT.value -> {
                        binding.imgLogo.setImageResource(R.drawable.aliance_ic)
                        this@MainActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                    }

                    ThemeApp.DARK.value -> {
                        binding.imgLogo.setImageResource(R.drawable.galactic_empire)
                        this@MainActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
                    }

                    ThemeApp.OS.value -> {
                        this@MainActivity.delegate.localNightMode =
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

                        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                            Configuration.UI_MODE_NIGHT_YES -> {binding.imgLogo.setImageResource(R.drawable.galactic_empire)}
                            Configuration.UI_MODE_NIGHT_NO -> {binding.imgLogo.setImageResource(R.drawable.aliance_ic)}
                        }
                    }
                }
            }
        }
    }

    private fun setDrawerListeners() {
        binding.apply {
            btnHome.setOnClickListener {
                supportFragmentManager.beginTransaction().replace(R.id.container_fragments,HomeFragment(),"home_fragment").commit()
                actionChangeCategory.invoke(Categories.HOME)
                selectCategoryDrawer(Categories.HOME)
                drawerActions()
                updateNameItem(View.GONE)
            }

            btnSearch.setOnClickListener {
                supportFragmentManager.beginTransaction().replace(R.id.container_fragments,SearchFragment(),"search_fragment").commit()
                actionChangeCategory.invoke(Categories.SEARCH)
                selectCategoryDrawer(Categories.SEARCH)
                drawerActions()
                updateNameItem(View.GONE)
            }

            btnPeople.setOnClickListener {
                actionChangeCategory.invoke(Categories.PEOPLE)
                selectCategoryDrawer(Categories.PEOPLE)
                drawerActions()

            }
            btnPlanet.setOnClickListener {
                actionChangeCategory.invoke(Categories.PLANETS)
                selectCategoryDrawer(Categories.PLANETS)
                drawerActions()

            }
            btnFilm.setOnClickListener {
                actionChangeCategory.invoke(Categories.FILMS)
                selectCategoryDrawer(Categories.FILMS)
                drawerActions()

            }
            btnSpecies.setOnClickListener {
                actionChangeCategory.invoke(Categories.SPECIES)
                selectCategoryDrawer(Categories.SPECIES)
                drawerActions()

            }
            btnVehicles.setOnClickListener {
                actionChangeCategory.invoke(Categories.VEHICLES)
                selectCategoryDrawer(Categories.VEHICLES)
                drawerActions()

            }
            btnStarships.setOnClickListener {
                actionChangeCategory.invoke(Categories.STARSHIPS)
                selectCategoryDrawer(Categories.STARSHIPS)
                drawerActions()

            }
        }
    }

    private fun setListeners() {
        binding.btnCloseFragmentUp.setOnClickListenerBounce {
            closeSettings()
        }
        binding.btnActionDrawer.setOnClickListenerBounce {
            drawerActions()
        }

        binding.btnExpandDrawer.setOnClickListener {
            expandDrawer()
        }

        binding.btnShrinkDrawer.setOnClickListener {
            shrinkDrawer()
        }

        binding.btnSettings.setOnClickListener {
            openSettings()
        }

        binding.containerUp.setOnClickListener {
            closeSettings()
        }
        binding.btnBackFragmentUp.setOnClickListener {
            onBackPressed()
        }
    }

    private fun closeSettings() {
        supportFragmentManager.beginTransaction().remove(settingFragment).commit()
        binding.containerUp.visibility = View.GONE
    }

    private fun openSettings() {
        drawerActions()
        supportFragmentManager.beginTransaction().replace(R.id.container_fragment_up, settingFragment,"settings_fragment").commit()
        binding.containerUp.visibility = View.VISIBLE
    }

    fun selectCategoryDrawer(category: Categories) {
        binding.textHome.setTextColor(ContextCompat.getColor(this, getThemeColor(this,R.attr.textAppColor)))
        binding.iconHome.setColorFilter(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))

        binding.textSearch.setTextColor(ContextCompat.getColor(this, getThemeColor(this,R.attr.textAppColor)))
        binding.iconSearch.setColorFilter(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))

        binding.textPeople.setTextColor(ContextCompat.getColor(this, getThemeColor(this,R.attr.textAppColor)))
        binding.iconPeople.setColorFilter(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))
        binding.textPlanets.setTextColor(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))
        binding.iconPlanets.setColorFilter(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))

        binding.textFilms.setTextColor(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))
        binding.iconFilms.setColorFilter(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))

        binding.textSpecies.setTextColor(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))
        binding.iconSpecies.setColorFilter(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))

        binding.textVehicles.setTextColor(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))


        binding.iconStarships.setColorFilter(
            ContextCompat.getColor(
                this,
                getThemeColor(this,R.attr.textAppColor)
            )
        )
        binding.iconVehicles.setColorFilter(
            ContextCompat.getColor(
                this,
                getThemeColor(this,R.attr.textAppColor)
            )
        )
        binding.textStarships.setTextColor(ContextCompat.getColor(this,  getThemeColor(this,R.attr.textAppColor)))
        when (category) {
            Categories.HOME -> {
                binding.textHome.setTextColor(ContextCompat.getColor(this,  getThemeColor(this,R.attr.sideAppColor)))
                binding.iconHome.setColorFilter(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
            }

            Categories.SEARCH -> {
                binding.textSearch.setTextColor(ContextCompat.getColor(this,  getThemeColor(this,R.attr.sideAppColor)))
                binding.iconSearch.setColorFilter(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
            }
            Categories.PEOPLE -> {
                binding.textPeople.setTextColor(ContextCompat.getColor(this,  getThemeColor(this,R.attr.sideAppColor)))
                binding.iconPeople.setColorFilter(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
            }

            Categories.PLANETS -> {
                binding.textPlanets.setTextColor(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
                binding.iconPlanets.setColorFilter(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
            }

            Categories.FILMS -> {
                binding.textFilms.setTextColor(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
                binding.iconFilms.setColorFilter(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
            }

            Categories.SPECIES -> {
                binding.textSpecies.setTextColor(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
                binding.iconSpecies.setColorFilter(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))

            }

            Categories.VEHICLES -> {
                binding.textVehicles.setTextColor(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
                binding.iconVehicles.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        getThemeColor(this,R.attr.sideAppColor)
                    )
                )
            }

            Categories.STARSHIPS -> {
                binding.textStarships.setTextColor(ContextCompat.getColor(this, getThemeColor(this,R.attr.sideAppColor)))
                binding.iconStarships.setColorFilter(
                    ContextCompat.getColor(
                        this,
                        getThemeColor(this,R.attr.sideAppColor)
                    )
                )
            }
        }
    }

    private fun expandDrawer() {
        binding.btnExpandDrawer.visibility = View.GONE
        binding.textHome.increaseViewWidthAnimation(250) {}
        binding.textSearch.increaseViewWidthAnimation(250) {}
        binding.textPeople.increaseViewWidthAnimation(250) {}
        binding.textPlanets.increaseViewWidthAnimation(250) {}
        binding.textFilms.increaseViewWidthAnimation(250) {}
        binding.textSpecies.increaseViewWidthAnimation(250) {}
        binding.textVehicles.increaseViewWidthAnimation(250) {}
        binding.textStarships.increaseViewWidthAnimation(250) {
            binding.btnShrinkDrawer.visibility = View.VISIBLE
        }
    }

    private fun shrinkDrawer() {
        binding.btnShrinkDrawer.visibility = View.GONE
        binding.textHome.increaseViewWidthAnimation(-250) {}
        binding.textSearch.increaseViewWidthAnimation(-250) {}
        binding.textPeople.increaseViewWidthAnimation(-250) {}
        binding.textPlanets.increaseViewWidthAnimation(-250) {}
        binding.textFilms.increaseViewWidthAnimation(-250) {}
        binding.textSpecies.increaseViewWidthAnimation(-250) {}
        binding.textVehicles.increaseViewWidthAnimation(-250) {}
        binding.textStarships.increaseViewWidthAnimation(-250) {
            binding.btnExpandDrawer.visibility = View.VISIBLE
        }
    }

    private fun setUi() {
        binding.containerUp.visibility = View.GONE
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragments, HomeFragment(),"categories_fragment").commit()
    }

    fun updateNameItem(status: Int) {
        binding.containerNameItem.visibility = status
    }

    fun setNameItem(name: String) {
        binding.textNameItem.text = name
    }

    fun setNameCategory(name: String) {
        binding.textNameCategory.text = name
    }

    fun getNameCategory() : String {
        return binding.textNameCategory.text.toString()
    }
    private fun drawerActions() {
        if (binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(Gravity.LEFT)
        } else {
            binding.drawer.openDrawer(GravityCompat.START)
        }
    }

    private fun getThemeColor(context: Context, color : Int): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(color, value, true)
        return value.resourceId
    }

    override fun onBackPressed() {
        if (supportFragmentManager.findFragmentById(R.id.container_fragment_up)?.tag == "settings_fragment") {
            closeSettings()
        } else {
            super.onBackPressed()
        }
    }

    fun changeTitleFragmentUp(title: String) {
        binding.textTitleFragmentUp.text = title
    }

    fun setNewTheme() {
        viewModel.getTheme()
    }
}