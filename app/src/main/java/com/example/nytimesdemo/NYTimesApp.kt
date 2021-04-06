package com.example.nytimesdemo

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class NYTimesApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}