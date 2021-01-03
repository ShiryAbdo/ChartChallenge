package com.shaimaa.fakhraldin.charts

import android.app.Application
import com.shaimaa.fakhraldin.charts.di.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoroutinesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoroutinesApplication)
            androidLogger()
            modules(modules)
        }
    }


}