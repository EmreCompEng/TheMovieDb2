package com.emreergun.themoviedb2.di.modules

import android.app.Application
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.emreergun.themoviedb2.repostiory.MoviesRepository
import com.emreergun.themoviedb2.ui.main.populermovies.PopulerMoviesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewComponent
import javax.inject.Singleton

@Module
@InstallIn(FragmentComponent::class)
object FragmentsModule{

    // Sadece Faragment oluştuğu zaman oluşur
    // Fragment'dan ayrıldığı zaman silinir
    // Fragment açıldığı zaman üretilir
    // her frangment için yeni instance doluşturulur

    @Provides
    fun providePopulerMoviesAdapter(requestManager: RequestManager, moviesRepository: MoviesRepository): PopulerMoviesAdapter {
        return PopulerMoviesAdapter(requestManager,moviesRepository)
    }



    @Provides
    fun provideGridLayoutManager(application: Application): GridLayoutManager {
        return GridLayoutManager(application, 2, GridLayoutManager.VERTICAL, false)
    }


}