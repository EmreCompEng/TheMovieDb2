package com.emreergun.themoviedb2.ui.main.populermovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    fun observePopulerMovies(){
        _moviesLiveData.value=MovieResource.Loading() // Loading....
        repository.getPopulerMovies(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : DisposableObserver<PopulerMovies>() {
                override fun onNext(populerMovies: PopulerMovies) {
                    _moviesLiveData.value=MovieResource.Success(populerMovies)  // Successs....
                }

                override fun onError(error: Throwable) {
                    _moviesLiveData.value=MovieResource.Error("error :${error.message}") // Error.....
                }

                override fun onComplete() {


                }

            })


    }

    // Sıralı Birleştirme
    private fun zipObservable() {
        _moviesLiveData.value=MovieResource.Loading()
        Observable
            .merge(
                repository.getPopulerMovies(1),
                repository.getPopulerMovies(2),
                repository.getPopulerMovies(3),
                repository.getPopulerMovies(4),
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object :DisposableObserver<PopulerMovies>(){
                override fun onNext(populerMovies: PopulerMovies) {
                    _moviesLiveData.value=MovieResource.Success(populerMovies)  // Successs....
                }

                override fun onError(error: Throwable) {
                    _moviesLiveData.value=MovieResource.Error("error :${error.message}") // Error.....
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }

            })

    }

    companion object {
        private const val TAG = "PopulerMoviesViewModel"
    }


}