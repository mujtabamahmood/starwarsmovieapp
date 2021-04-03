package com.a.androidmovieapp

import android.app.Application
import com.a.androidmovieapp.di.component.AppComponent
import com.a.androidmovieapp.di.component.DaggerAppComponent
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.Stetho

open class MoviesApplication : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDI()
        initStetho()

    }

    private fun initDI(){
        appComponent = DaggerAppComponent.builder().build()
    }

    private fun initStetho(){
        if(BuildConfig.DEBUG) Stetho.initializeWithDefaults(this)
    }
}