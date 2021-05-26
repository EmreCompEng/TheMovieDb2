package com.emreergun.themoviedb2.ui.main.populermovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.emreergun.themoviedb2.R
import com.emreergun.themoviedb2.models.populermovies.Result
import com.emreergun.themoviedb2.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class PopulerMoviesViewHolder(view:View):RecyclerView.ViewHolder(view){
    val movieImage:ImageView=view.findViewById(R.id.image_imgv_populer)
    val movieTitle:TextView=view.findViewById(R.id.name_txt_populer)
    val movieDate:TextView=view.findViewById(R.id.date_txt_populer)
}

class PopulerMoviesAdapter @Inject constructor(
    private val requestManager: RequestManager
):RecyclerView.Adapter<PopulerMoviesViewHolder>() {


    private var movieList= mutableListOf<Result>() // created empty array
    fun addPopulerMovieList(list:List<Result>){
        movieList.addAll(list)
        notifyDataSetChanged() // yeni liste için adapter güncelle
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopulerMoviesViewHolder {
        val root=LayoutInflater.from(parent.context).inflate(R.layout.populer_movie_item,parent,false)
        return PopulerMoviesViewHolder(root)

    }

    override fun onBindViewHolder(holder: PopulerMoviesViewHolder, position: Int) {
        val movie=movieList[position] // current movie

       // Image
        requestManager
            .load(Constants.IMAGE_PREV_PATH+movie.posterPath)
            .into(holder.movieImage)
        // Title
        holder.movieTitle.text=movie.title

        // Date
        holder.movieDate.text=movie.releaseDate

    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}