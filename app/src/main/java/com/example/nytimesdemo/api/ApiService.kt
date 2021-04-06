package com.example.nytimesdemo.api


import com.example.nytimesdemo.models.BestSellersResult
import com.example.nytimesdemo.models.BookByListResult
import com.example.nytimesdemo.util.Constants
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService {

    @GET(Constants.API_BOOK_LIST)
     suspend fun getBestSellers(@Query("api-key") apikey : String) : Response<BestSellersResult>

    @GET(Constants.API_NAME_LIST)
    suspend fun getListByName(@Query("api-key") apikey : String, @Query("list") list : String) : Response<BookByListResult>

}