package com.emreergun.themoviedb2.network

import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.util.Constants
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    //movie/popular
    @GET("movie/popular")
    fun getPopulerMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY)
    : Single<PopulerMovies>

}