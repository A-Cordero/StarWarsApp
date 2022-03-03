package com.aridev.cordero.starwarsapp.domain

import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.core.sharedPreference.SharedPref
import javax.inject.Inject

class SaveThemeApp @Inject constructor(
    private val sharedPref: SharedPref
) {
    operator fun invoke(theme : ThemeApp, callback : () -> Unit ) {
        sharedPref.write(SharedPref.ThemeApp,theme.value)
        callback.invoke()
    }
}