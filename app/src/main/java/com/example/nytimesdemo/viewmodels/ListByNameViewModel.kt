package com.example.nytimesdemo.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimesdemo.BuildConfig
import com.example.nytimesdemo.api.ApiService
import com.example.nytimesdemo.models.ResultsItem
import com.example.nytimesdemo.models.ResultsItemByName
import com.example.nytimesdemo.util.Resource

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class ListByNameViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {
    private val bestSellers = MutableLiveData<Resource<List<ResultsItemByName?>?>>()
    val _bestSellers: LiveData<Resource<List<ResultsItemByName?>?>>
        get() = bestSellers


    val listName = MutableLiveData<String>()

    fun data(id: String?) {
        Log.d("TAG", "Current View Model Id : " + id)
        listName.value = id ?: ""
        fetchBestSellers()
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        bestSellers.postValue(Resource.error("Something Went Wrong", null))
    }

    fun fetchBestSellers() {
        viewModelScope.launch(exceptionHandler) {
            bestSellers.postValue(Resource.loading(null))
            apiService.getListByName(BuildConfig.API_KEY, listName.value.toString()).let {
                if (it.isSuccessful) {
                    bestSellers.postValue(Resource.success(it.body()?.results))

                } else {
                    Log.d("TAG", "Network Call Failed")
                    bestSellers.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }


}