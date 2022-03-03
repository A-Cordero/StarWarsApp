package com.aridev.cordero.starwarsapp.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.domain.GetThemeApp
import com.aridev.cordero.starwarsapp.domain.SaveThemeApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChangeThemeViewModel @Inject constructor(
    val saveThemeApp: SaveThemeApp,
    val getThemeApp : GetThemeApp
): ViewModel() {
    private val _uiState = MutableLiveData<ViewState>()
    val uiState: LiveData<ViewState> = _uiState

    private val _updateTheme = MutableLiveData<Boolean>()
    val updateTheme: LiveData<Boolean> = _updateTheme

    data class ViewState(
        val lightSelected: Boolean = true,
        val darkSelected: Boolean = false,
        val osSelected: Boolean = false
    )

    init {
        getMyThemeApp()
    }

    fun trySelectThemeOs(check: Boolean) {
        when (check) {
            true -> {
                trySelectTheme(ThemeApp.OS)
            }
            false -> {
                trySelectTheme(ThemeApp.LIGHT)
            }
        }
    }

    fun trySelectTheme(myTheme: ThemeApp) {
        saveThemeApp(theme = myTheme) {
            _updateTheme.value = true
            getMyThemeApp()
        }
    }

    private fun getMyThemeApp() {
        getThemeApp {
            when (it) {
                ThemeApp.LIGHT.value -> {
                    _uiState.value = ViewState(
                        lightSelected = true,
                        darkSelected = false,
                        osSelected = false
                    )
                }

                ThemeApp.DARK.value -> {
                    _uiState.value = ViewState(
                        lightSelected = false,
                        darkSelected = true,
                        osSelected = false
                    )
                }

                ThemeApp.OS.value -> {
                    _uiState.value = ViewState(
                        lightSelected = false,
                        darkSelected = false,
                        osSelected = true
                    )
                }
            }
        }
    }
}
