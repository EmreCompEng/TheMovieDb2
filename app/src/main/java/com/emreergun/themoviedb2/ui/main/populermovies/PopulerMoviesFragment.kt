package com.emreergun.themoviedb2.ui.main.populermovies

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emreergun.themoviedb2.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopulerMoviesFragment : Fragment(R.layout.fragment_populer_movies) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: CircularProgressIndicator
    private lateinit var morePopMoviesBtn: MaterialButton

    // Injecte olacak
    private val viewModel: PopulerMoviesViewModel by viewModels()

    @Inject
    lateinit var adapter: PopulerMoviesAdapter

    @Inject
    lateinit var gridLayManager: GridLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViews(view)

        viewModel.observePopulerMovies()
        viewModel.moviesLiveData.observe(viewLifecycleOwner,{
            when(it.status){
                MovieResource.MovieStatus.LOADING->{
                    Log.d(TAG, "onViewCreated: LOADING...")
                    progressBar.isVisible=true
                }
                MovieResource.MovieStatus.SUCCESS->{
                    Log.d(TAG, "onViewCreated: SUCCESS...")
                    adapter.addPopulerMovieList(it.data!!.results)
                    progressBar.isVisible=false

                }
                MovieResource.MovieStatus.ERROR->{
                    Log.d(TAG, "onViewCreated: ERROR... => ${it.message}")
                    progressBar.isVisible=false
                }
            }
        })

        // Add new Populer Pages
        morePopMoviesBtn.setOnClickListener {
            viewModel.observePopulerMovies()
        }

        //Detech Last Of recyclerview satate
        recyclerView.addOnScrollListener(object:RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                morePopMoviesBtn.isVisible = !recyclerView.canScrollVertically(1)
            }
        })



    }

    private fun setViews(view: View) {
        progressBar=view.findViewById(R.id.populer_circularProgress)
        morePopMoviesBtn=view.findViewById(R.id.more_populer_material_btn)
        recyclerView=view.findViewById(R.id.populer_recycler_view)
        recyclerView.layoutManager=gridLayManager
        recyclerView.adapter=adapter
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