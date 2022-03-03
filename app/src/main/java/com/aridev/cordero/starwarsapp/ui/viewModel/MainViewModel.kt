package com.aridev.cordero.starwarsapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aridev.cordero.starwarsapp.domain.GetThemeApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getThemeApp: GetThemeApp
): ViewModel() {

    private val _themeState = MutableLiveData<String>()
    val themeState : LiveData<String> = _themeState

    init {
        getTheme()
    }

    fun getTheme() {
        getThemeApp.invoke {
            _themeState.value = it
        }
    }
}