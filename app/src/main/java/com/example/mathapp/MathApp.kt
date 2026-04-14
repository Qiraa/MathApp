package com.example.mathapp

import android.app.Application
import com.example.mathapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MathApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MathApp)
            modules(appModule)
        }
    }
}
