package com.aridev.cordero.starwarsapp.ui.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aridev.cordero.starwarsapp.data.Item
import com.aridev.cordero.starwarsapp.data.model.Categories
import com.aridev.cordero.starwarsapp.domain.GetItemList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel  @Inject constructor(
    private val getListItem  : GetItemList
): ViewModel() {

    private val _itemList = MutableLiveData<List<Item>>()
    val itemList: LiveData<List<Item>> = _itemList

    private val _progress = MutableLiveData<Boolean>()
    val progress : LiveData<Boolean> = _progress
    private var nextUrl = Categories.PEOPLE.url

    fun getPeople(category : Categories?) {
        _progress.value = true
        if (category != null) nextUrl = category.url
        viewModelScope.launch {
            getListItem.getItemList(nextUrl) { success, error ->
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
                    _itemList.postValue(list)
                } else {
                    Log.d("App error : ", error)
                }
                _progress.postValue(false)
            }
        }
    }

    fun changeCategory( category : Categories) {
        nextUrl = category.url
        getPeople(null)
    }
}