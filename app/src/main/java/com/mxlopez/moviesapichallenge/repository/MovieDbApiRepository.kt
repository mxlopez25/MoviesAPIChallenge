package com.mxlopez.moviesapichallenge.repository

import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.models.MovieResult
import com.mxlopez.moviesapichallenge.services.RetrofitInstance
import com.mxlopez.moviesapichallenge.util.Constants
import retrofit2.Response
import retrofit2.http.Query

class MovieDbApiRepository {
    suspend fun getMovies(apiKey: String = Constants.API_KEY, sortBy: String = "popularity.desc"): Response<MovieResult> {
        return RetrofitInstance.api.getMovies(apiKey, sortBy)
    }
}