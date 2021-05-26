package com.emreergun.themoviedb2.repostiory

import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.network.MovieApi
import com.emreergun.themoviedb2.util.Constants
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieApi:MovieApi
){

    // Get All Populer Movies
    fun getPopulerMovies(): Single<PopulerMovies> {
        return movieApi.getPopulerMovies()
    }
}