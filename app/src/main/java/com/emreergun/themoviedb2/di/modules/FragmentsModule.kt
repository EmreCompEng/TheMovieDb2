package com.emreergun.themoviedb2.di.modules

import com.bumptech.glide.RequestManager
import com.emreergun.themoviedb2.repostiory.MoviesRepository
import com.emreergun.themoviedb2.ui.main.populermovies.PopulerMoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object FragmentsModule{

    @Provides
    fun providePopulerMoviesAdapter(requestManager: RequestManager, moviesRepository: MoviesRepository): PopulerMoviesAdapter {
        return PopulerMoviesAdapter(requestManager,moviesRepository)
    }

}