package com.mxlopez.moviesapichallenge.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.mxlopez.moviesapichallenge.R
import com.mxlopez.moviesapichallenge.models.Movie
import com.mxlopez.moviesapichallenge.util.Constants
import com.mxlopez.moviesapichallenge.util.MovieUtils

class MovieListAdapter(private val list: List<Movie>): RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
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