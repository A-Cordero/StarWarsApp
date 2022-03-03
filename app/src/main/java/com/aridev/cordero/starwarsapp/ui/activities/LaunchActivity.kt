package com.aridev.cordero.starwarsapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.databinding.ActivityLaunchBinding
import com.aridev.cordero.starwarsapp.ui.viewModel.LaunchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LaunchActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLaunchBinding
    private val viewModel : LaunchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setViewModel()
    }

    private fun setViewModel() {
        viewModel.apply {
            countDown.observe(this@LaunchActivity,{
                startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                finish()
            })

            themeState.observe(this@LaunchActivity) {
                when (it) {
                    ThemeApp.LIGHT.value -> {
                        this@LaunchActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
                    }

                    ThemeApp.DARK.value -> {
                        this@LaunchActivity.delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_YES
                    }

                    ThemeApp.OS.value -> {
                        this@LaunchActivity.delegate.localNightMode =
                            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                }
            }
        }
    }
}