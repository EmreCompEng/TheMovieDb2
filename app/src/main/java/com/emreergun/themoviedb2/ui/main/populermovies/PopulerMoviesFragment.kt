package com.emreergun.themoviedb2.ui.main.populermovies

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.emreergun.themoviedb2.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopulerMoviesFragment : Fragment(R.layout.fragment_populer_movies) {

    // Injecte olacak
    private val viewModel: PopulerMoviesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.observePopulerMovies()
        viewModel.moviesLiveData.observe(viewLifecycleOwner,{
            when(it.status){
                MovieResource.MovieStatus.LOADING->{
                    Log.d(TAG, "onViewCreated: LOADING...")
                }
                MovieResource.MovieStatus.SUCCESS->{
                    Log.d(TAG, "onViewCreated: SUCCESS...")
                }
                MovieResource.MovieStatus.ERROR->{
                    Log.d(TAG, "onViewCreated: ERROR... => ${it.message}")
                }
            }
        })



    }

    companion object {
        private const val TAG = "PopulerMoviesFragment"
    }

}


/*      Navigate other fragment with safe args

 val movieId=movie.id // get Movie Id For Movie Detail
                Log.i(Common.LOG_TAG,"$movieId")

                // init action for other fragment data
                val action=HomeFragmentDirections
                    .actionHomeFragmentToTvShowDetailFragment(movieId)

                // Novigat Other Fragment
                Navigation.findNavController(view)
                    .navigate(action)

 */