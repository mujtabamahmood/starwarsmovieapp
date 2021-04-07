package com.a.androidmovieapp.di.module

import com.a.androidmovieapp.data.datasource.remote.MoviesServices
import com.a.androidmovieapp.data.datasource.remote.MoviesDataSourceImpl
import com.a.androidmovieapp.data.repository.MoviesRepositoryImpl
import com.a.androidmovieapp.domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun providesAppRepository(api: MoviesServices): MoviesRepository {
        val moviesDataSourceImpl = MoviesDataSourceImpl(api)
        return MoviesRepositoryImpl(moviesDataSourceImpl)
    }
}