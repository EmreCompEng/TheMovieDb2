package com.emreergun.themoviedb2.repostiory

import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.models.populermovies.Result
import com.emreergun.themoviedb2.network.MovieApi
import com.emreergun.themoviedb2.util.Constants
import com.emreergun.themoviedb2.util.PrefManager
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val movieApi:MovieApi,
    private val prefManager: PrefManager
){

    // Get All Populer Movies
    fun getPopulerMovies(pageNumber:Int=1): Observable<PopulerMovies> {
        return movieApi.getPopulerMovies(Constants.API_KEY,pageNumber)
    }
    // Favorite Movie With Pref-----
    fun favButtonClicked(movie: Result){
        // Eğer Film Favori ise listeden çıkar
        if (checkIsFavoriteMovie(movie)){
            prefManager.deleteFavoriteMovie(movie)
        }
        else
            prefManager.setFavoriteMovie(movie)
    }

    fun getFavoriteMovies(): Array<Result> {
        return prefManager.getFavoriteMovieList().reversed().toTypedArray()
    }
    fun checkIsFavoriteMovie(movie: Result): Boolean {
        return prefManager.checkIsFavorite(movie)
    }

    // Git control



}