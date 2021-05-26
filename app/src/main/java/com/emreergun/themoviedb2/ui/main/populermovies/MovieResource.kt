package com.emreergun.themoviedb2.ui.main.populermovies

// A generic class that contains data and status about loading this data.
sealed class MovieResource<T>(
    val status: MovieStatus,
    val data: T? = null,
    val message: String? = null
) {
    enum class MovieStatus {
        LOADING, SUCCESS, ERROR
    }

    class Success<T>(data: T) : MovieResource<T>(MovieStatus.SUCCESS,data)
    class Loading<T>(data: T? = null) : MovieResource<T>(MovieStatus.LOADING,data)
    class Error<T>(message: String, data: T? = null) : MovieResource<T>(MovieStatus.ERROR,data, message)
}