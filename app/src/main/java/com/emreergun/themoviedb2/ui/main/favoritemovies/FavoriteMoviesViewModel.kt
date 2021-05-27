package com.emreergun.themoviedb2.ui.main.favoritemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.repostiory.MoviesRepository
import com.emreergun.themoviedb2.ui.main.populermovies.MovieResource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteMoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _liveData = MutableLiveData<MovieResource<PopulerMovies>>()

    val favoriteMovieLiveData: LiveData<MovieResource<PopulerMovies>>
        get() = _liveData

    fun getFavoriteMovies(){

    }





}