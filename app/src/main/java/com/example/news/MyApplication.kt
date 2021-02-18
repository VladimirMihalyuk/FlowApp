package com.example.news

import android.app.Application
import com.example.news.data
import com.example.news.network
import com.example.news.viewModels
import org.koin.core.context.startKoin
import org.koin.android.ext.koin.androidContext
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate(){
        super.onCreate()

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        startKoin {
            // declare used Android context
            androidContext(this@MyApplication)
            // declare modules
            modules(
                network,
                data,
                viewModels
            )
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}