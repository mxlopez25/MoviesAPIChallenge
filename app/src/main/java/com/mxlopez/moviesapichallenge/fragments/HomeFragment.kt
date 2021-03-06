package com.mxlopez.moviesapichallenge.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mxlopez.moviesapichallenge.R
import com.mxlopez.moviesapichallenge.adapters.MovieListAdapter
import com.mxlopez.moviesapichallenge.databinding.FragmentHomeBinding
import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.repository.MovieDbApiRepository
import com.mxlopez.moviesapichallenge.util.Constants
import com.mxlopez.moviesapichallenge.viewmodels.SharedViewModel
import com.mxlopez.moviesapichallenge.viewmodels.SharedViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var sharedViewModelFactory: SharedViewModelFactory
    private lateinit var repository: MovieDbApiRepository
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        repository = MovieDbApiRepository()
        sharedViewModelFactory = SharedViewModelFactory(repository)
        prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)

        sharedViewModel = ViewModelProvider(requireActivity(), sharedViewModelFactory).get(SharedViewModel::class.java)
        val add: (Movie) -> Unit = { m ->
            Constants.addMovieToFavorite(m, requireContext(), prefs)
        }
        val remove: (Movie) -> Unit = { m ->
            Constants.removeMovieFromFavorite(m, requireContext(), prefs)
        }
        sharedViewModel.movies.observe(viewLifecycleOwner) {
            binding.rvMovieList.adapter = MovieListAdapter(it!!, add, remove)
            binding.rvMovieList.layoutManager = LinearLayoutManager(context)

        }

        return binding.root
    }

    companion object {
    }
}