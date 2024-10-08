package com.eventapp.mealswithroom.ui.categories.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eventapp.mealswithroom.networking.response.categories.Categories
import com.eventapp.mealswithroom.ui.categories.repositories.MealsCategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealsCategoriesViewModel(private val repository: MealsCategoryRepository = MealsCategoryRepository()): ViewModel() {

    private val _categories = MutableLiveData<List<Categories>>()
    val categories: LiveData<List<Categories>> = _categories

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun fetchCategories() {
        _isLoading.value = true
        Log.d("TAG_COROUTINES", "about to launch a coroutine")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("TAG_COROUTINES", "launching a coroutine")
            try {
                val meals = repository.getMealsCategories()
                Log.d("TAG_COROUTINES", "we have received sync data")
                _categories.postValue(meals)
            } catch (e: Exception) {
                // Handle any errors here
                e.printStackTrace()
            } finally {
                // Set loading to false after data is fetched
                _isLoading.postValue(false)
            }
        }
        Log.d("TAG_COROUTINES", "other work")
    }
}