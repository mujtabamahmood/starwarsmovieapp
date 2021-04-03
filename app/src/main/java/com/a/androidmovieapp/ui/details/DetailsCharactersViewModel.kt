package com.a.androidmovieapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.a.androidmovieapp.data.common.MoviesResult
import com.a.androidmovieapp.domain.models.MovieModel
import com.a.androidmovieapp.domain.models.PlanetModel
import com.a.androidmovieapp.domain.models.SpecieModel
import com.a.androidmovieapp.domain.usecases.GetMoviesUseCase
import com.a.androidmovieapp.domain.usecases.GetPlanetUseCase
import com.a.androidmovieapp.domain.usecases.GetSpeciesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailsCharactersViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getPlanetUseCase: GetPlanetUseCase,
    private val getSpeciesUseCase: GetSpeciesUseCase
) : ViewModel() {

    private val _resultMovie = MutableLiveData<MoviesResult<List<MovieModel>>>()
    val resultMovie: LiveData<MoviesResult<List<MovieModel>>> = _resultMovie

    fun getMovies(movieUrls: List<String>) {
        viewModelScope.launch {
            getMoviesUseCase(movieUrls).collect {
                _resultMovie.postValue(it)
            }
        }
    }

    private val _resultSpecie = MutableLiveData<MoviesResult<List<SpecieModel>>>()
    val resultSpecie: LiveData<MoviesResult<List<SpecieModel>>> = _resultSpecie

    fun getSpecies(movieUrls: List<String>) {
        viewModelScope.launch {
            getSpeciesUseCase(movieUrls).collect {
                _resultSpecie.postValue(it)
            }
        }
    }


    private val _resultPlanet = MutableLiveData<MoviesResult<PlanetModel>>()
    val resultPlanet: LiveData<MoviesResult<PlanetModel>> = _resultPlanet

    fun getPlanet(planetUrl: String) {
        viewModelScope.launch {
            getPlanetUseCase(planetUrl).collect {
                _resultPlanet.postValue(it)
            }
        }
    }
}