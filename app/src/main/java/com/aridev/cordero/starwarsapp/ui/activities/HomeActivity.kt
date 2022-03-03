package com.aridev.cordero.starwarsapp.ui.activities

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.databinding.ActivityHomeBinding
import com.aridev.cordero.starwarsapp.ui.fragments.CategoriesFragment
import com.aridev.cordero.starwarsapp.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private val viewModel : HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUi()
        setListeners()
        setObservers()
    }

    private fun setObservers() {
        viewModel.apply {
            themeState.observe(this@HomeActivity) {
                when (it) {
                    ThemeApp.LIGHT.value -> {
                        binding.imgHome.setImageResource(R.drawable.obiwan)
                        this@HomeActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                    }

                    ThemeApp.DARK.value -> {
                        binding.imgHome.setImageResource(R.drawable.home_dark_screen)
                        this@HomeActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
                    }

                    ThemeApp.OS.value -> {
                        this@HomeActivity.delegate.localNightMode =
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM

                        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                            Configuration.UI_MODE_NIGHT_YES -> {binding.imgHome.setImageResource(R.drawable.home_dark_screen)}
                            Configuration.UI_MODE_NIGHT_NO -> {binding.imgHome.setImageResource(R.drawable.obiwan)}
                        }
                    }
                }
            }
        }
    }

    private fun setListeners() {
        binding.btnEnter.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun setUi() {

    }
}