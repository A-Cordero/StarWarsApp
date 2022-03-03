package com.aridev.cordero.starwarsapp.domain

import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.core.sharedPreference.SharedPref
import javax.inject.Inject

class GetThemeApp @Inject constructor(
    private val sharedPref: SharedPref
)  {
    operator fun invoke(callback :(theme : String) -> Unit) {
        callback.invoke(sharedPref.read(SharedPref.ThemeApp, ThemeApp.LIGHT.value))
    }
}