package com.emreergun.themoviedb2.util

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import javax.inject.Inject

class PrefManager @Inject constructor(
    private val application: Application,
    private val gson:Gson
) {

    private val sharedPrefs =
        application.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
    private val editor=sharedPrefs.edit()

    fun checkIsFavorite(movieId: String): Boolean {
        val list=getFavoriteMovieList()
        return list.contains(movieId) ?: false
    }

    fun deleteFavoriteMovie(movieId: String){
        var favoriteMovieList=getFavoriteMovieList()
        val newList=favoriteMovieList.toMutableList()
        newList.remove(movieId)
        favoriteMovieList=newList.toTypedArray()
        saveMovieList(favoriteMovieList)
    }


    fun setFavoriteMovie(movieId: String) {
        var favoriteList = getFavoriteMovieList()
        // Eğer Liste Null Döner ise
        val newList = favoriteList.toMutableList()
        newList.add(movieId)
        favoriteList = newList.toTypedArray()

        saveMovieList(favoriteList)
    }

    fun getFavoriteMovieList(): Array<String> {
        val jsonText: String = sharedPrefs.getString(Constants.FAVORITE_MOVIE_KEY_PREF, null).toString()
        val list = gson.fromJson(jsonText, Array<String>::class.java) //EDIT: gso to gson
        return list ?: emptyArray()
    }
    private fun saveMovieList(list: Array<String>){
        val jsonText = gson.toJson(list)
        editor.putString(Constants.FAVORITE_MOVIE_KEY_PREF, jsonText)
        editor.apply()
    }

}