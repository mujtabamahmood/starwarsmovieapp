package com.a.androidmovieapp.di.component

import com.a.androidmovieapp.di.module.NetworkModule
import com.a.androidmovieapp.di.module.RepositoriesModule
import com.a.androidmovieapp.di.module.ViewModelModule
import com.a.androidmovieapp.ui.details.DetailsCharactersActivity
import com.a.androidmovieapp.ui.search.SearchCharactersActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class, NetworkModule::class, RepositoriesModule::class]
)
interface AppComponent {
    fun inject(searchCharactersActivity: SearchCharactersActivity)
    fun inject(detailsCharactersActivity: DetailsCharactersActivity)
}