package com.aridev.cordero.starwarsapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.databinding.FragmentChangeThemeBinding
import com.aridev.cordero.starwarsapp.ui.activities.MainActivity
import com.aridev.cordero.starwarsapp.ui.viewModel.ChangeThemeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangeThemeFragment : Fragment() {
    private var _binding: FragmentChangeThemeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ChangeThemeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentChangeThemeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setUi()
            setListeners()
            setAdapter()
            setObservers()
    }

    private fun setUi() {
        (requireActivity() as MainActivity).changeTitleFragmentUp("Choose your side")
    }

    private fun updateTheme() {
        (requireActivity() as MainActivity).setNewTheme()
    }

    private fun setListeners() {
        binding.btnThemeLight.setOnClickListener { viewModel.trySelectTheme(ThemeApp.LIGHT)}
        binding.btnThemeDark.setOnClickListener { viewModel.trySelectTheme(ThemeApp.DARK)}
        binding.swTheme.setOnClickListener { viewModel.trySelectThemeOs(binding.swTheme.isChecked) }
    }

    private fun setAdapter() {

    }

    private fun setObservers() {
        viewModel.uiState.observe(viewLifecycleOwner){render(it)}
        viewModel.updateTheme.observe(viewLifecycleOwner){updateTheme()}
    }

    private fun render(viewState: ChangeThemeViewModel.ViewState) {


        when( viewState.osSelected) {
            true -> {
                binding.swTheme.isChecked = viewState.osSelected
                binding.btnThemeLight.isEnabled = false
                binding.btnThemeDark.isEnabled = false
                binding.btnThemeLight.setBackgroundResource(R.drawable.select_background)
                binding.btnThemeDark.setBackgroundResource(R.drawable.unselected_background)
            }

            false -> {
                binding.swTheme.isChecked = viewState.osSelected
                when( viewState.lightSelected) {
                    true -> {
                        binding.btnThemeLight.isEnabled = false
                        binding.btnThemeLight.setBackgroundResource(R.drawable.select_background)
                    }

                    false -> {
                        binding.btnThemeLight.isEnabled = true
                        binding.btnThemeLight.setBackgroundResource(R.drawable.unselected_background)
                    }
                }

                when( viewState.darkSelected) {
                    true -> {
                        binding.btnThemeDark.isEnabled = false
                        binding.btnThemeDark.setBackgroundResource(R.drawable.select_background)
                    }

                    false -> {
                        binding.btnThemeDark.isEnabled = true
                        binding.btnThemeDark.setBackgroundResource(R.drawable.unselected_background)
                    }
                }
            }
        }
    }

}