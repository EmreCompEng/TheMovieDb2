package com.emreergun.themoviedb2.network

import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import io.reactivex.Single
import retrofit2.http.GET

interface MovieApi {

    //movie/popular
    @GET("/movie/popular")
    fun getPopulerMovies(): Single<PopulerMovies>

}