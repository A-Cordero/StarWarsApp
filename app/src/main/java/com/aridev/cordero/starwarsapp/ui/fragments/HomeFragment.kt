package com.aridev.cordero.starwarsapp.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.aridev.cordero.starwarsapp.R
import com.aridev.cordero.starwarsapp.core.dataApp.ThemeApp
import com.aridev.cordero.starwarsapp.core.dataApp.openUrlLink
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.databinding.FragmentHomeBinding
import com.aridev.cordero.starwarsapp.ui.activities.MainActivity
import com.aridev.cordero.starwarsapp.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setUi()
            setListeners()
            setObservers()
    }

    private fun setObservers() {
        viewModel.apply {
            themeState.observe(viewLifecycleOwner) {
                when (it) {
                    ThemeApp.LIGHT.value -> {
                        binding.imgHome.setImageResource(R.drawable.obiwan)
                        binding.textProlog.text = resources.getString(R.string.prolog_light)
                        binding.textTittle.text = resources.getString(R.string.light_force)
                    }

                    ThemeApp.DARK.value -> {
                        binding.imgHome.setImageResource(R.drawable.home_dark_screen)
                        binding.textProlog.text = resources.getString(R.string.prolog_dark)
                        binding.textTittle.text = resources.getString(R.string.dark_force)
                    }

                    ThemeApp.OS.value -> {
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
        binding.btnGithub.setOnClickListener {
            requireContext().openUrlLink("https://github.com/A-Cordero")
        }
        binding.btnEnter.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.container_fragments,CategoriesFragment(),"categories_fragment")
                .commit()
            (requireActivity() as MainActivity).selectCategoryDrawer(Categories.PEOPLE)

        }

        (requireActivity() as MainActivity).actionChangeCategory = { category ->
            if(category != Categories.HOME && category != Categories.SEARCH) {
                val fragment = CategoriesFragment()
                val bundle = Bundle()
                bundle.putSerializable("categories",category)
                fragment.arguments = bundle
                (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.container_fragments, fragment,"categories_fragment").commit()

            } else if( category == Categories.SEARCH) {
                val fragment = SearchFragment()
                (requireActivity() as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.container_fragments, fragment,"search_fragment").commit()

            }
        }
    }

    private fun setUi() {
        (requireActivity() as MainActivity).setNameCategory(Categories.HOME.value)
        (requireActivity() as MainActivity).updateNameItem(View.GONE)
    }
}