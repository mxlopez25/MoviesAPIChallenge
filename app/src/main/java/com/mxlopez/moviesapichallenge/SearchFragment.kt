package com.mxlopez.moviesapichallenge

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mxlopez.moviesapichallenge.R
import com.mxlopez.moviesapichallenge.adapters.MovieListAdapter
import com.mxlopez.moviesapichallenge.databinding.FragmentSearchBinding
import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.repository.MovieDbApiRepository
import com.mxlopez.moviesapichallenge.util.Constants
import com.mxlopez.moviesapichallenge.viewmodels.SharedViewModel
import com.mxlopez.moviesapichallenge.viewmodels.SharedViewModelFactory

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var adapter: MovieListAdapter

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var sharedViewModelFactory: SharedViewModelFactory
    private lateinit var repository: MovieDbApiRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        sharedViewModelFactory = SharedViewModelFactory(MovieDbApiRepository())
        sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(SharedViewModel::class.java)

        loadList()

        binding.btnSearchMovies.setOnClickListener {
            val value = binding.etSearchField.text.toString()
            loadList(value)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadList()
    }

    private fun loadList(value: String? = "") {
        val add: (Movie) -> Unit = { m ->
            Constants.addMovieToFavorite(m)
        }
        val remove: (Movie) -> Unit = { m ->
            Constants.removeMovieFromFavorite(m)
        }
        if(value.isNullOrEmpty()) {
            adapter = MovieListAdapter(Constants.getFavoritesMovies(), add, remove)
        } else {
            adapter = MovieListAdapter(Constants.getFavoritesMovies().filter { it.title.contains(value) }, add, remove)
        }

        binding.rvSearchFavorite.layoutManager = LinearLayoutManager(context)
        binding.rvSearchFavorite.adapter = adapter
    }

    companion object {

    }
}