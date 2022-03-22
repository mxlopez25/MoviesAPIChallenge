package com.mxlopez.moviesapichallenge.util

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mxlopez.moviesapichallenge.R
import com.mxlopez.moviesapichallenge.models.Movie

class Constants {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/%s"
        const val API_KEY = "0d677b16a44d2b5a79edf325bc1ee9b7"

        const val DISCOVERY_MOVIES_URL = "discover/movie"

        const val MOVIE_ID_TAG = "MOVIE_ID_TAG"
        const val MOVIE_NAME_TAG = "MOVIE_NAME_TAG"
        const val MOVIE_RELEASE_TAG = "MOVIE_RELEASE_TAG"
        const val MOVIE_OVERVIEW_TAG = "MOVIE_OVERVIEW_TAG"
        const val MOVIE_IMAGE_TAG = "MOVIE_IMAGE_TAG"


        private var favoritesMovies: MutableList<Movie> = mutableListOf()

        var movieList: MutableList<Movie> = mutableListOf()

        fun initiateFavorites(context: Context, prefs: SharedPreferences) {
            val gson = Gson()
            val s = prefs.getString("SAVED_FAVORITES", "")
            if (!s.isNullOrEmpty()) {
                val mutableListMovieType = object : TypeToken<MutableList<Movie>>() {}.type
                val data: MutableList<Movie> = gson.fromJson(s, mutableListMovieType)
                favoritesMovies = data
            }
        }

        fun addMovieToFavorite(movie: Movie, context: Context, prefs: SharedPreferences) {
            if (favoritesMovies.isNullOrEmpty()) {
                favoritesMovies = mutableListOf(movie)
            } else {
                favoritesMovies.add(movie)
            }

            with(prefs.edit()) {
                val gson = Gson()
                putString(
                    "SAVED_FAVORITES",
                    gson.toJson(favoritesMovies)
                )
                apply()
            }
        }

        fun removeMovieFromFavorite(movie: Movie, context: Context, prefs: SharedPreferences) {
            if (favoritesMovies.isNotEmpty()) {
                favoritesMovies.remove(movie)
            }

            with(prefs.edit()) {
                val gson = Gson()
                putString(
                    "SAVED_FAVORITES",
                    gson.toJson(favoritesMovies)
                )
                apply()
            }
        }

        fun getFavoritesMovies(): MutableList<Movie> {
            return favoritesMovies
        }

        fun isMovieFavorite(movie: Movie): Boolean {
            return favoritesMovies.find { it.id == movie.id } != null
        }
    }
}