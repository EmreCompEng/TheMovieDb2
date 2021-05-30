package com.emreergun.themoviedb2.util

import android.app.Application
import android.content.Context
import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.models.populermovies.Result
import com.google.gson.Gson
import javax.inject.Inject

class PrefManager @Inject constructor(
    private val application: Application,
    private val gson:Gson
) {

    private val sharedPrefs =
        application.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    private val editor=sharedPrefs.edit()

    fun checkIsFavorite(movie: Result): Boolean {
        val list=getFavoriteMovieList()
        return list.contains(movie) ?: false
    }

    fun deleteFavoriteMovie(movie: Result){
        var favoriteMovieList=getFavoriteMovieList()
        val newList=favoriteMovieList.toMutableList()
        newList.remove(movie)
        favoriteMovieList=newList.toTypedArray()
        saveMovieList(favoriteMovieList)
    }


    fun setFavoriteMovie(movie: Result) {
        var favoriteList = getFavoriteMovieList()
        // Eğer Liste Null Döner ise
        val newList = favoriteList.toMutableList()
        newList.add(movie)
        favoriteList = newList.toTypedArray()

        saveMovieList(favoriteList)
    }

    fun getFavoriteMovieList(): Array<Result> {
        val jsonText: String = sharedPrefs.getString(Constants.FAVORITE_MOVIE_KEY_PREF, null).toString()
        val list = gson.fromJson(jsonText, Array<Result>::class.java) //EDIT: gso to gson
        return list ?: emptyArray()
    }
    private fun saveMovieList(list: Array<Result>){
        val jsonText = gson.toJson(list)
        editor.putString(Constants.FAVORITE_MOVIE_KEY_PREF, jsonText)
        editor.apply()
    }

}