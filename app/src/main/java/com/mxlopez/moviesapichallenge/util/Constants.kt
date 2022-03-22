package com.mxlopez.moviesapichallenge.util

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

        fun addMovieToFavorite(movie: Movie, prefs: SharedPreferences) {
            if(favoritesMovies.isNullOrEmpty()) {
                favoritesMovies = mutableListOf(movie)
            } else {
                favoritesMovies.add(movie)
            }
        }

        fun removeMovieFromFavorite(movie: Movie, prefs: SharedPreferences) {
            if(favoritesMovies.isNotEmpty()) {
                favoritesMovies.remove(movie)
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