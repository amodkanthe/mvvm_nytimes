package com.example.nytimesdemo.models

import com.google.gson.annotations.SerializedName

data class BestSellersResult(

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null,

	@field:SerializedName("num_results")
	val numResults: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class UpdatedBy(val updated: String?)

data class ResultsItem(

	@field:SerializedName("newest_published_date")
	val newestPublishedDate: String? = null,

	@field:SerializedName("oldest_published_date")
	val oldestPublishedDate: String? = null,

	@field:SerializedName("list_name")
	val listName: String? = null,

	@field:SerializedName("list_name_encoded")
	val listNameEncoded: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("updated")
	val updated: String? = null
)
