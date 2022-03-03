package com.aridev.cordero.starwarsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentSettingsBinding.inflate(layoutInflater, container, false)
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
    }

    private fun setListeners() {
        binding.btnChangeTheme.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left,
                    R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right,
                    R.anim.exit_left_to_right
                ).replace(R.id.container_fragment_up , ChangeThemeFragment(),"")
                .addToBackStack(null)
                .commit()
        }
        binding.btnAboutStyle.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_right_to_left,
                    R.anim.exit_right_to_left,
                    R.anim.enter_left_to_right,
                    R.anim.exit_left_to_right
                ).replace(R.id.container_fragment_up , AboutFragment(),"")
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setAdapter() {

    }

    private fun setObservers() {

    }
}