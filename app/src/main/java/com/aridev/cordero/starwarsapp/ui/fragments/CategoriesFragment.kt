package com.aridev.cordero.starwarsapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.aridev.cordero.starwarsapp.core.dataApp.addSwiperStyleVp
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.databinding.FragmentCategoriesBinding
import com.aridev.cordero.starwarsapp.ui.activities.DetailActivity
import com.aridev.cordero.starwarsapp.ui.activities.MainActivity
import com.aridev.cordero.starwarsapp.ui.adapter.HomeAdapter
import com.aridev.cordero.starwarsapp.ui.dialog.ProgressDialog
import com.aridev.cordero.starwarsapp.ui.viewModel.CategoriesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null
    private val binding get() = _binding!!

    private val adapterHome = HomeAdapter()

    private val viewModel: CategoriesViewModel by viewModels()
    private lateinit var progress : ProgressDialog
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
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
        val category : Categories? = arguments?.getSerializable("categories") as Categories?
        if(category != null) {
            (requireActivity() as MainActivity).setNameCategory(category.value)
        } else {
            (requireActivity() as MainActivity).setNameCategory(Categories.PEOPLE.value)
        }
        progress = ProgressDialog(requireContext())
    }

    private fun setListeners() {
        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(adapterHome.list.isNotEmpty())
                (requireActivity() as MainActivity).setNameItem(adapterHome.getItemPageSelected(position))
            }
        })

        binding.vpHome.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if( state == ViewPager2.SCROLL_STATE_IDLE ||
                    state == ViewPager2.SCROLL_STATE_DRAGGING) {
                    if (binding.vpHome.currentItem == adapterHome.list.size/2) {
                        viewModel.getPeople(null)
                    }
                }
            }
        })

        (requireActivity() as MainActivity).actionChangeCategory = { category ->
            if(category != Categories.HOME && category != Categories.SEARCH) {
                (requireActivity() as MainActivity).setNameCategory(category.name)
                viewModel.changeCategory(category)
                adapterHome.clearItems()
            }
        }

    }

    private fun setAdapter() {
        //adapterHome
        adapterHome.list = viewModel.itemList.value ?: emptyList()
        binding.vpHome.addSwiperStyleVp()
        binding.vpHome.adapter = adapterHome


        adapterHome.actionCategory = { item ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("item", item.url)
            intent.putExtra("category",(requireActivity() as MainActivity).getNameCategory())
            startActivity(intent)
        }
    }

    private fun setObservers() {
        viewModel.apply {
            itemList.observe(viewLifecycleOwner) {
                adapterHome.addItems(it)
                (requireActivity() as MainActivity).updateNameItem(View.VISIBLE)
            }

            progress.observe(viewLifecycleOwner) {
                this@CategoriesFragment.progress.setProgress(it)
            }
            val category : Categories? = arguments?.getSerializable("categories") as Categories?
            getPeople(category)
        }
    }
}