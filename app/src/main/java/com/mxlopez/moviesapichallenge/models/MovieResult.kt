package com.mxlopez.moviesapichallenge.models

data class MovieResult(
    val page: Int,
    val results: MutableList<Movie>
)
