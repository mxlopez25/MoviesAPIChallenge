package com.mxlopez.moviesapichallenge.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.repository.MovieDbApiRepository
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: MovieDbApiRepository): ViewModel() {
    private var _movies = MutableLiveData<MutableList<Movie>>()
    private var _favorites = MutableLiveData<MutableList<Movie>>()
    private var _searched = MutableLiveData<MutableList<Movie>>()

    val movies: LiveData<MutableList<Movie>> = _movies
    val favorites: LiveData<MutableList<Movie>> = _favorites
    val searched: LiveData<MutableList<Movie>> = _searched

    fun fetchMovies() {
        viewModelScope.launch {
            val response = repository.getMovies()
            if(response.isSuccessful) {
                Log.d("SharedViewModel - fetchMovies", response.body().toString())
                _movies.value = response.body()?.results
            }
        }
    }

    fun searchShows(name: String = "") {
        viewModelScope.launch {

        }
    }

    fun addToFavorites(movie: Movie) {
        viewModelScope.launch {
            if(_favorites.value.isNullOrEmpty()) {
                _favorites.value = mutableListOf(movie)
            } else {
                _favorites.value!!.add(movie)
            }
        }
    }

    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch {
            _favorites.value!!.remove(movie)
        }
    }
}