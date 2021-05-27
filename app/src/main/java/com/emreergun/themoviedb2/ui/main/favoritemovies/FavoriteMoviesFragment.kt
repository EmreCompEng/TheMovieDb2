package com.emreergun.themoviedb2.ui.main.favoritemovies

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emreergun.themoviedb2.R
import com.emreergun.themoviedb2.ui.main.populermovies.PopulerMoviesAdapter
import javax.inject.Inject

class FavoriteMoviesFragment : Fragment(R.layout.fragment_favorite_movies) {
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var adapter: PopulerMoviesAdapter

    @Inject
    lateinit var gridLayManager: GridLayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=view.findViewById(R.id.favorites_recycler_view)
        recyclerView.layoutManager=gridLayManager
        recyclerView.adapter=adapter






    }

}