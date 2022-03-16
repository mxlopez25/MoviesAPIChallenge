package com.mxlopez.moviesapichallenge.services

import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.models.MovieResult
import com.mxlopez.moviesapichallenge.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {
    @GET(Constants.DISCOVERY_MOVIES_URL)
    suspend fun getMovies(@Query("api_key") apiKey: String = Constants.API_KEY, @Query("sort_by") sortBy: String = "popularity.desc") : Response<MovieResult>
}