package com.aridev.cordero.starwarsapp.ui.viewModel

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aridev.cordero.starwarsapp.domain.GetThemeApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LaunchViewModel @Inject constructor(
    private val getThemeApp: GetThemeApp
) : ViewModel() {
    private val _themeState = MutableLiveData<String>()
    val themeState : LiveData<String> = _themeState

    private val _countDown = MutableLiveData<Boolean>()
    val countDown : LiveData<Boolean> = _countDown

    init {
        getTheme()
        Handler(Looper.getMainLooper()).postDelayed({
            _countDown.value = true
        },2000)
    }

    fun getTheme() {
        getThemeApp.invoke {
            _themeState.value = it
        }
    }
}