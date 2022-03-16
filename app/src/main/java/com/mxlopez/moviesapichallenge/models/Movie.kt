package com.mxlopez.moviesapichallenge.models

import com.google.gson.annotations.SerializedName

data class Movie(
    val id: Int,
    val adult: Boolean = false,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("genders_ids")
    val gendersIds: Array<String>?,
    @SerializedName("original_language")
    val originalLanguage: String = "en",
    @SerializedName("original_title")
    val originalTitle: String = "",
    val overview: String = "",
    val popularity: Float = 0.0F,
    @SerializedName("poster_path")
    val posterPath: String = "",
    val releaseDate: String = "",
    val title: String = "",
    val video: Boolean = false,
    @SerializedName("vote_average")
    val voteAverage: Float = 0.0F,
    @SerializedName("vote_count")
    val voteCount: Int = 0

)
