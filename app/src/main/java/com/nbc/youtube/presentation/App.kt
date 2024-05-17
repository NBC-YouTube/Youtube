package com.nbc.youtube.presentation

import android.app.Application
import com.nbc.youtube.presentation.di.AppContainer

class App: Application() {

    val appContainer: AppContainer = AppContainer(this)
}