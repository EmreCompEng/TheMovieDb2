package com.emreergun.themoviedb2.ui.main.populermovies

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.emreergun.themoviedb2.R
import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.models.populermovies.Result
import com.emreergun.themoviedb2.repostiory.MoviesRepository
import com.emreergun.themoviedb2.util.Constants
import javax.inject.Inject

class PopulerMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val movieImage: ImageView = view.findViewById(R.id.movieImage)
    val movieFavorite: ImageView = view.findViewById(R.id.favButton)
    val movieTitle: TextView = view.findViewById(R.id.movieName)
    val movieDate: TextView = view.findViewById(R.id.movieDate)

}

class PopulerMoviesAdapter @Inject constructor(
    private val requestManager: RequestManager,
    private val moviesRepository: MoviesRepository
) : RecyclerView.Adapter<PopulerMoviesViewHolder>() {

    private var movieList = mutableListOf<Result>() // created empty array
    fun addPopulerMovieList(list: List<Result>) {
        movieList.addAll(list) // Yeni Liste Ekle
        notifyDataSetChanged() // yeni liste için adapter güncelle
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulerMoviesViewHolder {
        val root =
            LayoutInflater.from(parent.context).inflate(R.layout.populer_movie_item2, parent, false)
        return PopulerMoviesViewHolder(root)

    }

    override fun onBindViewHolder(holder: PopulerMoviesViewHolder, position: Int) {
        val movie = movieList[position] // current movie

        // Set All Data in Views
        setViews(holder, movie)

        // Fav Button Click Listener
        holder.movieFavorite.setOnClickListener {
            moviesRepository.favButtonClicked(movie)
            notifyItemChanged(position)
        }



    }

    private fun setViews(holder: PopulerMoviesViewHolder, movie: Result) {
        // Image
        requestManager
            .load(Constants.IMAGE_PREV_PATH + movie.posterPath)
            .into(holder.movieImage)
        // Title
        holder.movieTitle.text = movie.title
        Log.d(TAG, "onBindViewHolder: overview")

        // Date
        holder.movieDate.text = movie.releaseDate

        // Check is Favorite
        // Eğer Favori ise
        if (moviesRepository.checkIsFavoriteMovie(movie)){
            requestManager
                .load(R.drawable.ic_fav_colored)
                .into(holder.movieFavorite)
        }
        else{
            requestManager
                .load(R.drawable.ic_fav_empty)
                .into(holder.movieFavorite)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    companion object {
        private const val TAG = "PopulerMoviesAdapter"
    }
}