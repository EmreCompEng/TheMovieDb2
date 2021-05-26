package com.emreergun.themoviedb2.ui.main.populermovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.emreergun.themoviedb2.models.populermovies.PopulerMovies
import com.emreergun.themoviedb2.repostiory.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
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

        // Loading data.....
        _moviesLiveData.value=MovieResource.Loading()

        // Fetch data....
        repository.getPopulerMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object :DisposableSingleObserver<PopulerMovies>(){
                override fun onSuccess(populerMovies: PopulerMovies) {
                    _moviesLiveData.value=MovieResource.Success(populerMovies)  // Successs....
                }

                override fun onError(error: Throwable) {
                    _moviesLiveData.value=MovieResource.Error("error :${error.message}") // Error.....
                }

            })




    }




}