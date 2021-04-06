package com.example.nytimesdemo.viewmodels


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nytimesdemo.BuildConfig
import com.example.nytimesdemo.api.ApiService
import com.example.nytimesdemo.models.ResultsItem
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
class BestSellersViewModel @Inject constructor(private val apiService: ApiService) : ViewModel(){
    private val bestSellers = MutableLiveData<Resource<List<ResultsItem?>?>>()
    val _bestSellers: LiveData<Resource<List<ResultsItem?>?>>
        get() = bestSellers

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        bestSellers.postValue(Resource.error("Something Went Wrong", null))
    }
    init {
        fetchBestSellers()
    }
     fun fetchBestSellers(){
        viewModelScope.launch(exceptionHandler) {
            bestSellers.postValue(Resource.loading(null))
            apiService.getBestSellers(BuildConfig.API_KEY).let {
                if(it.isSuccessful){
                    bestSellers.postValue(Resource.success(it.body()?.results))

                }else {
                    Log.d("TAG","Network Call Failed")
                    bestSellers.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }
    }



}