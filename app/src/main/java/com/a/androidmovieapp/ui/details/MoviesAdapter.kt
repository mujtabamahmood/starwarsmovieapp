package com.a.androidmovieapp.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.a.androidmovieapp.databinding.ItemMovieBinding
import com.a.androidmovieapp.domain.models.MovieModel
import com.a.androidmovieapp.utils.MAX_LINES_MOVIE

class MoviesAdapter(private val moviesList: List<MovieModel>) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(private val view: ItemMovieBinding) :
        RecyclerView.ViewHolder(view.root) {
            init {
                view.root.setOnClickListener{
                    val expanded : Boolean = moviesList[adapterPosition].isExpanded
                    moviesList[adapterPosition].isExpanded = !expanded
                    notifyItemChanged(adapterPosition)
                }
            }
        fun bindTo(movie: MovieModel){
            with(view){
                itemTitle.text = movie.title
                itemDescription.text = movie.description
                if(movie.isExpanded) itemDescription.maxLines = Integer.MAX_VALUE
                else itemDescription.maxLines = MAX_LINES_MOVIE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        ItemMovieBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
            )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(moviesList[position])
    }

    override fun getItemCount() = moviesList.size
}