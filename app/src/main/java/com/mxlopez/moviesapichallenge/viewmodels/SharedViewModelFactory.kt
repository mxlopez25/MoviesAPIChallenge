package com.mxlopez.moviesapichallenge.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mxlopez.moviesapichallenge.repository.MovieDbApiRepository

class SharedViewModelFactory(private val repository: MovieDbApiRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repository) as T
    }
}