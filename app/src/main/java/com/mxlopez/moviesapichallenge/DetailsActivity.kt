package com.mxlopez.moviesapichallenge

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import coil.api.load
import com.mxlopez.moviesapichallenge.databinding.ActivityDetailsBinding
import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.util.Constants
import com.mxlopez.moviesapichallenge.viewmodels.SharedViewModel

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var mId = 0
    private var isFavorite = false
    private lateinit var movie: Movie
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        prefs = this.getPreferences(Context.MODE_PRIVATE)
        val bundle = intent.extras
        if (bundle != null) {
            mId = bundle.getInt(Constants.MOVIE_ID_TAG)
            movie = Constants.movieList.find { it.id == mId }!!
            isFavorite = Constants.isMovieFavorite(movie)

            val mName = bundle.getString(Constants.MOVIE_NAME_TAG)
            val mOverview = bundle.getString(Constants.MOVIE_OVERVIEW_TAG)
            val releasedDate = bundle.getString(Constants.MOVIE_RELEASE_TAG)
            val posterPath = bundle.getString(Constants.MOVIE_IMAGE_TAG)
            binding.tvMovName.text = mName
            binding.tvMovOverview.text = mOverview
            binding.tvMovReleaseDate.text = String.format(
                applicationContext.resources.getString(R.string.movie_released_label),
                releasedDate
            )
            binding.ivMovImage.load(String.format(Constants.BASE_IMAGE_URL, posterPath))
        }

        if (isFavorite) {
            binding.btnAddToFavorite.visibility = View.GONE
            binding.btnRemoveFavorite.visibility = View.VISIBLE
        } else {
            binding.btnRemoveFavorite.visibility = View.GONE
            binding.btnAddToFavorite.visibility = View.VISIBLE
        }

        binding.btnAddToFavorite.setOnClickListener {
            Constants.addMovieToFavorite(movie, applicationContext, prefs)
            updateFavoriteButtons()
        }

        binding.btnRemoveFavorite.setOnClickListener {
            Constants.removeMovieFromFavorite(movie, applicationContext, prefs)
            updateFavoriteButtons()
        }
    }

    fun updateFavoriteButtons() {
        isFavorite = Constants.isMovieFavorite(movie)
        if (isFavorite) {
            binding.btnAddToFavorite.visibility = View.GONE
            binding.btnRemoveFavorite.visibility = View.VISIBLE
        } else {
            binding.btnRemoveFavorite.visibility = View.GONE
            binding.btnAddToFavorite.visibility = View.VISIBLE
        }
    }
}