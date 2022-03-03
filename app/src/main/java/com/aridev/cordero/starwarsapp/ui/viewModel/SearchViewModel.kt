package com.aridev.cordero.starwarsapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aridev.cordero.starwarsapp.core.dataApp.baseUrl
import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.data.model.Category
import com.aridev.cordero.starwarsapp.domain.GetItemList
import com.aridev.cordero.starwarsapp.domain.SearchItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchItem  : SearchItem
) : ViewModel() {

    private val _itemList = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>> = _itemList

    private val _listCategories = MutableLiveData<List<Category>>()
    val listCategories : LiveData<List<Category>> = _listCategories

    private val _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean> = _progress

    private val _emptyList = MutableLiveData<Boolean>()
    val emptyList : LiveData<Boolean> = _emptyList

    private var nextUrl = ""
    var categorySelected = Categories.PEOPLE

    fun search(query : String) {
        _progress.value = true
        val url = baseUrl+categorySelected.value.lowercase()+"/?search="+query
        viewModelScope.launch {
            searchItem.getSearch(url) { success, error ->
                if (error.isNullOrEmpty()) {
                    nextUrl = success?.next ?: ""
                    var list : List<Item> = success?.results ?: arrayListOf()
                    list.forEach {
                        if(it.name != null) {
                        } else if(it.title != null) {
                        }
                        it.name = it.name?.lowercase() ?: ""
                        it.title = it.title?.lowercase() ?: ""
                    }
                    if(list.isEmpty()) _emptyList.postValue(true)
                    else _itemList.postValue(list)
                } else {
                    Log.d("App error : ", error)
                }
                _progress.postValue(false)
            }
        }
    }

    fun updateCategories(position : Int) {
        var list = _listCategories.value!!
        list.forEach {
            it.status = false
        }
        list[position].status = true
        categorySelected = list[position].type
        _listCategories.value = list
    }

    fun getCategories() {
        val list = emptyList<Category>().toMutableList()
        categorySelected = Categories.PEOPLE
        list.add(Category(Categories.PEOPLE,true))
        list.add(Category(Categories.PLANETS, false))
        list.add(Category(Categories.FILMS,false))
        list.add(Category(Categories.SPECIES,false))
        list.add(Category(Categories.VEHICLES,false))
        list.add(Category(Categories.STARSHIPS,false))

        _listCategories.value = list
    }

}