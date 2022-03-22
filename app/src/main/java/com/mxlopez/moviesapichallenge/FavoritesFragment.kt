package com.mxlopez.moviesapichallenge

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mxlopez.moviesapichallenge.R
import com.mxlopez.moviesapichallenge.adapters.MovieListAdapter
import com.mxlopez.moviesapichallenge.databinding.FragmentFavoritesBinding
import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.util.Constants

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: MovieListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        loadList()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        loadList()
    }

    private fun loadList() {
        val add: (Movie) -> Unit = { m ->
            Constants.addMovieToFavorite(m)
        }
        val remove: (Movie) -> Unit = { m ->
            Constants.removeMovieFromFavorite(m)
        }

        adapter = MovieListAdapter(Constants.getFavoritesMovies(), add, remove)
        binding.rvFavorites.adapter = adapter
        binding.rvFavorites.layoutManager = LinearLayoutManager(context)
    }

    companion object {

    }
}