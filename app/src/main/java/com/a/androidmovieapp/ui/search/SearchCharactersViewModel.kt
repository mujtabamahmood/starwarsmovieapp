package com.a.androidmovieapp.ui.search

import androidx.lifecycle.ViewModel
import com.a.androidmovieapp.domain.usecases.SearchCharactersUseCase
import javax.inject.Inject

class SearchCharactersViewModel @Inject constructor(private val searchCharactersUseCase: SearchCharactersUseCase)
    :ViewModel(){

        suspend fun searchCharacters(input : String) = searchCharactersUseCase(input)
}