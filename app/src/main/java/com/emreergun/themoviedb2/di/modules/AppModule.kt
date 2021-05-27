package com.emreergun.themoviedb2.di.modules

import android.app.Application
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.emreergun.themoviedb2.R
import com.emreergun.themoviedb2.network.MovieApi
import com.emreergun.themoviedb2.repostiory.MoviesRepository
import com.emreergun.themoviedb2.ui.main.populermovies.PopulerMoviesAdapter
import com.emreergun.themoviedb2.util.Constants
import com.emreergun.themoviedb2.util.PrefManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  // Uygulama boyunca heryerden ulaşabileceğimiz annotation
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }


    @Singleton
    @Provides
    fun provideMovieSRepository(movieApi: MovieApi,prefManager: PrefManager): MoviesRepository {
        return MoviesRepository(movieApi,prefManager)
    }


    @Singleton
    @Provides
    fun provideMovieApi(retrofit: Retrofit): MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRequestOpitons(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.ic_emre_logo)
            .fitCenter()
            //.optionalFitCenter()
    }

    @Singleton
    @Provides
    fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }


    @Singleton
    @Provides
    fun providePopulerMoviesAdapter(requestManager: RequestManager,moviesRepository: MoviesRepository): PopulerMoviesAdapter {
        return PopulerMoviesAdapter(requestManager,moviesRepository)
    }


    @Singleton
    @Provides
    fun provideGridLayoutManager(application: Application): GridLayoutManager {
        return GridLayoutManager(application, 2, GridLayoutManager.VERTICAL, false)
    }



    @Singleton
    @Provides
    fun providGson(): Gson {
        return Gson()
    }

    @Singleton
    @Provides
    fun providePrefManager(application: Application,gson: Gson): PrefManager {
        return PrefManager(application,gson)
    }





}