package com.emreergun.themoviedb2.repostiory

import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.network.MovieApi
import com.emreergun.themoviedb2.util.Constants
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieApi:MovieApi
){

    // Get All Populer Movies
    fun getPopulerMovies(pageNumber:Int=1): Observable<PopulerMovies> {
        return movieApi.getPopulerMovies(Constants.API_KEY,pageNumber)
    }
}