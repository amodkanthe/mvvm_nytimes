package com.example.nytimesdemo.models

import com.google.gson.annotations.SerializedName

data class BookByListResult(

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("last_modified")
	val lastModified: String? = null,

	@field:SerializedName("results")
	val results: List<ResultsItemByName?>? = null,

	@field:SerializedName("num_results")
	val numResults: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
)

data class ReviewsItem(

	@field:SerializedName("article_chapter_link")
	val articleChapterLink: String? = null,

	@field:SerializedName("book_review_link")
	val bookReviewLink: String? = null,

	@field:SerializedName("first_chapter_link")
	val firstChapterLink: String? = null,

	@field:SerializedName("sunday_review_link")
	val sundayReviewLink: String? = null
)

data class ResultsItemByName(

	@field:SerializedName("isbns")
	val isbns: List<IsbnsItem?>? = null,

	@field:SerializedName("dagger")
	val dagger: Int? = null,

	@field:SerializedName("asterisk")
	val asterisk: Int? = null,

	@field:SerializedName("book_details")
	val bookDetails: List<BookDetailsItem?>? = null,

	@field:SerializedName("list_name")
	val listName: String? = null,

	@field:SerializedName("display_name")
	val displayName: String? = null,

	@field:SerializedName("weeks_on_list")
	val weeksOnList: Int? = null,

	@field:SerializedName("bestsellers_date")
	val bestsellersDate: String? = null,

	@field:SerializedName("amazon_product_url")
	val amazonProductUrl: String? = null,

	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem?>? = null,

	@field:SerializedName("rank")
	val rank: Int? = null,

	@field:SerializedName("published_date")
	val publishedDate: String? = null,

	@field:SerializedName("rank_last_week")
	val rankLastWeek: Int? = null
)

data class BookDetailsItem(

	@field:SerializedName("contributor_note")
	val contributorNote: String? = null,

	@field:SerializedName("contributor")
	val contributor: String? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("price")
	val price: String? = null,

	@field:SerializedName("age_group")
	val ageGroup: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("publisher")
	val publisher: String? = null,

	@field:SerializedName("primary_isbn10")
	val primaryIsbn10: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("primary_isbn13")
	val primaryIsbn13: String? = null
)

data class IsbnsItem(

	@field:SerializedName("isbn13")
	val isbn13: String? = null,

	@field:SerializedName("isbn10")
	val isbn10: String? = null
)
