package com.emreergun.themoviedb2.ui.main.populermovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.repostiory.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@HiltViewModel // ViewModel için Hilt Kullanımı
class PopulerMoviesViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _moviesLiveData = MutableLiveData<MovieResource<PopulerMovies>>()

    // getter fonksiyonu
    val moviesLiveData: LiveData<MovieResource<PopulerMovies>>
        get() = _moviesLiveData


    // Fetch data in repostiory with retrofit http
    fun observePopulerMovies() {
        if (currentPage <= 997)
            zipObservable()
    }

    // Sıralı Birleştirme
    private var currentPage = 1 // Max Page 1000
    private fun zipObservable() {
        _moviesLiveData.value = MovieResource.Loading()
        Observable
            .merge(
                repository.getPopulerMovies(currentPage),
                repository.getPopulerMovies(currentPage + 1),
                repository.getPopulerMovies(currentPage + 2),
                repository.getPopulerMovies(currentPage + 3),
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : DisposableObserver<PopulerMovies>() {
                override fun onNext(populerMovies: PopulerMovies) {
                    Log.d(TAG, "onNext: Current Page $currentPage")
                    _moviesLiveData.value= MovieResource.Success(populerMovies)  // Successs....
                }

                override fun onError(error: Throwable) {
                    _moviesLiveData.value =
                        MovieResource.Error("error :${error.message}") // Error.....
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                    Log.d(TAG, "onComplete: Last Page :$currentPage")
                    currentPage += 4
                }

            })

    }

    companion object {
        private const val TAG = "PopulerMoviesViewModel"
    }


}