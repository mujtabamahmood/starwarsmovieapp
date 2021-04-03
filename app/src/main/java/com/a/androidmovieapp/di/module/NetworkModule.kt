package com.a.androidmovieapp.di.module

import com.a.androidmovieapp.data.common.BASE_URL
import com.a.androidmovieapp.data.common.datasource.remote.MoviesServices
import com.facebook.stetho.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        const val IO_TIMEOUT = 30L
    }

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(IO_TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG)
            client.addNetworkInterceptor(StethoInterceptor())
        return client.build()

    }


    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()


    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): MoviesServices =
        retrofit.create(MoviesServices::class.java)

}