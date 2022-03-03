package com.aridev.cordero.starwarsapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.databinding.FragmentSearchBinding
import com.aridev.cordero.starwarsapp.ui.activities.MainActivity
import android.view.inputmethod.EditorInfo

import android.widget.TextView

import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.aridev.cordero.starwarsapp.core.dataApp.*
import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.data.model.Category
import com.aridev.cordero.starwarsapp.ui.activities.DetailActivity
import com.aridev.cordero.starwarsapp.ui.adapter.CategoriesAdapter
import com.aridev.cordero.starwarsapp.ui.adapter.SearchAdapter
import com.aridev.cordero.starwarsapp.ui.dialog.ProgressDialog
import com.aridev.cordero.starwarsapp.ui.viewModel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val adapter = SearchAdapter()
    lateinit var progressDialog : ProgressDialog
    private val adapterCategory = CategoriesAdapter()
    private val viewModel : SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            setUi()
            setListeners()
            setObservers()
    }

    private fun setUi() {
        progressDialog = ProgressDialog(requireContext())
        binding.rvSearch.visibility = View.VISIBLE
        binding.containerEmpty.visibility = View.GONE
        (requireActivity() as MainActivity).setNameCategory(Categories.SEARCH.value)
        (requireActivity() as MainActivity).updateNameItem(View.GONE)
    }

    private fun setListeners() {
        binding.tilSearch.searchMode() {
            binding.edtSearch.clearText()
        }

        binding.edtSearch.onChangeListener {
            binding.tilSearch.endIconStatus(it)
        }

        binding.edtSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            val handled = false
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.search(v.text.toString())
                v.hideSoftInput()
            }
            handled
        })
    }

    private fun setAdapterCategories(list : List<Category>) {
        adapterCategory.list = list
        binding.rvCategory.adapter = adapterCategory
        adapterCategory.actionCategory = { position, direction, size, category ->
            binding.rvCategory.scrollToPosition(position)
            viewModel.updateCategories(position)
        }
    }
    private fun setAdapter(list : List<Item>) {
        adapter.list = list
        binding.rvSearch.adapter = adapter
        adapter.actionSearch = { item ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("item", item.url)
            intent.putExtra("category",viewModel.categorySelected.value)
            startActivity(intent)
        }
    }

    private fun setObservers() {
        viewModel.apply {
            itemList.observe(viewLifecycleOwner) {
                binding.rvSearch.visibility = View.VISIBLE
                binding.containerEmpty.visibility = View.GONE
                setAdapter(it)
            }

            listCategories.observe(viewLifecycleOwner) {
                setAdapterCategories(it)
            }

            progress.observe(viewLifecycleOwner) {
                progressDialog.setProgress(it)
            }
            emptyList.observe(viewLifecycleOwner) {
                binding.rvSearch.visibility = View.GONE
                binding.containerEmpty.visibility = View.VISIBLE
            }
            getCategories()
        }
    }
}