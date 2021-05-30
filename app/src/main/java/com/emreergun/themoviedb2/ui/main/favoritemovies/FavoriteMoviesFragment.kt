package com.emreergun.themoviedb2.ui.main.favoritemovies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emreergun.themoviedb2.R
import com.emreergun.themoviedb2.ui.main.populermovies.MovieResource
import com.emreergun.themoviedb2.ui.main.populermovies.PopulerMoviesAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoriteMoviesFragment : Fragment(R.layout.fragment_favorite_movies) {
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var adapter: PopulerMoviesAdapter

    @Inject
    lateinit var gridLayManager: GridLayoutManager

    private val viewModel:FavoriteMoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.favorites_recycler_view)
        recyclerView.layoutManager=gridLayManager
        recyclerView.adapter=adapter


        viewModel.observeFavoriteMovies()
        viewModel.favoriteMovieLiveData.observe(viewLifecycleOwner,{
            when(it.status){
                MovieResource.MovieStatus.LOADING->{
                    Log.d(TAG, "onViewCreated: Loading...")
                }
                MovieResource.MovieStatus.SUCCESS->{
                    Log.d(TAG, "onViewCreated: Sucess...")
                    adapter.addPopulerMovieList(it.data!!.toList())
                }
                MovieResource.MovieStatus.ERROR->{
                    Log.d(TAG, "onViewCreated: Error...")
                }
            }
        })



    }

    companion object {
        private const val TAG = "FavoriteMoviesFragment"
    }

}