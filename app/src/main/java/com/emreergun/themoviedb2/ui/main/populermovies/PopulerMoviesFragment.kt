package com.emreergun.themoviedb2.ui.main.populermovies

import androidx.fragment.app.Fragment
import com.emreergun.themoviedb2.R


class PopulerMoviesFragment : Fragment(R.layout.fragment_populer_movies,) {

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