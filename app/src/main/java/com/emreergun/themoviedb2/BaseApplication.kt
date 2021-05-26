package com.emreergun.themoviedb2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Hilt Android App Seviyesinde Başlatılır
// En Zirve Yerdir => Singleton
@HiltAndroidApp
class BaseApplication : Application() {
}