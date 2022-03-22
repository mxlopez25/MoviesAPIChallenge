package com.mxlopez.moviesapichallenge.viewmodels

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mxlopez.moviesapichallenge.R
import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.repository.MovieDbApiRepository
import com.mxlopez.moviesapichallenge.util.MovieUtils
import kotlinx.coroutines.launch

class SharedViewModel(private val repository: MovieDbApiRepository): ViewModel() {
    private var _movies = MutableLiveData<MutableList<Movie>>()

    val movies: LiveData<MutableList<Movie>> = _movies

    fun fetchMovies(context: Context, prefs: SharedPreferences) {
        viewModelScope.launch {
            if(MovieUtils().isOnline(context)) {
                val response = repository.getMovies()
                if(response.isSuccessful) {
                    _movies.value = response.body()?.results
                    with (prefs.edit()) {
                        val gson = Gson()
                        putString(context.resources.getString(R.string.saved_movies), gson.toJson(_movies.value))
                        apply()
                    }
                }
            } else {
                Toast.makeText(context, "App is offline", Toast.LENGTH_SHORT).show()
                val gson = Gson()
                val s = prefs.getString(context.resources.getString(R.string.saved_movies), "")
                val mutableListMovieType = object : TypeToken<MutableList<Movie>>() {}.type
                var data: MutableList<Movie> = gson.fromJson(s, mutableListMovieType)
                _movies.value = data
            }
        }
    }
}