package com.mxlopez.moviesapichallenge.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mxlopez.moviesapichallenge.DetailsActivity
import com.mxlopez.moviesapichallenge.R
import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.util.Constants
import com.mxlopez.moviesapichallenge.util.MovieUtils

class MovieListAdapter(private val list: List<Movie>, private val addToFavorite: (movie: Movie) -> Unit, private val removeFromFavorite: (movie: Movie) -> Unit): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private lateinit var ctx: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.movie_cell_layout, parent, false)
        ctx = parent.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvMovieName.text = list[position].title
        holder.ivMoviePoster.load(String.format(Constants.BASE_IMAGE_URL, list[position].posterPath))
        holder.tvMovieRate.text = MovieUtils().parsePopularity(list[position].popularity)

        holder.clMovieCell.setOnClickListener {
            val m = list[position]
            val intent = Intent(ctx, DetailsActivity::class.java)
            intent.putExtra(Constants.MOVIE_ID_TAG, m.id)
            intent.putExtra(Constants.MOVIE_NAME_TAG, m.title)
            intent.putExtra(Constants.MOVIE_OVERVIEW_TAG, m.overview)
            intent.putExtra(Constants.MOVIE_RELEASE_TAG, m.releaseDate)
            intent.putExtra(Constants.MOVIE_IMAGE_TAG, m.posterPath)

            ctx.startActivity(intent)
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ivMoviePoster: ImageView = itemView.findViewById(R.id.ivMoviePoster)
        val tvMovieName: TextView = itemView.findViewById(R.id.tvMovieName)
        val tvMovieRate: TextView = itemView.findViewById(R.id.tvMoviewRate)
        val clMovieCell: ConstraintLayout = itemView.findViewById(R.id.clMovieCell)
    }
}