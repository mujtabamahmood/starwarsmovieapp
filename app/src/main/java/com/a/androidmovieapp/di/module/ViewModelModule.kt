package com.a.androidmovieapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.a.androidmovieapp.di.viewmodel.DaggerViewModelFactory
import com.a.androidmovieapp.di.viewmodel.ViewModelKey
import com.a.androidmovieapp.ui.details.DetailsCharactersViewModel
import com.a.androidmovieapp.ui.search.SearchCharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchCharactersViewModel::class)
    abstract fun bindSearchViewModel(searchCharactersViewModel: SearchCharactersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsCharactersViewModel::class)
    abstract fun bindDetailsViewModel(detailsCharactersViewModel: DetailsCharactersViewModel): ViewModel


    @Binds abstract fun bindViewModelFactory(factory: DaggerViewModelFactory): ViewModelProvider.Factory

}