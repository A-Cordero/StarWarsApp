package com.aridev.cordero.starwarsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.core.dataApp.openUrlLink
import com.aridev.cordero.starwarsapp.databinding.FragmentAboutBinding
import com.aridev.cordero.starwarsapp.ui.activities.MainActivity

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
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
        (requireActivity() as MainActivity).changeTitleFragmentUp("About")

    }

    private fun setListeners() {
        binding.btnMoure.setOnClickListener {
            requireContext().openUrlLink("https://github.com/mouredev")
        }

        binding.btnAri.setOnClickListener {
            requireContext().openUrlLink("https://github.com/A-Cordero")
        }
    }

    private fun setAdapter() {

    }

    private fun setObservers() {

    }
}