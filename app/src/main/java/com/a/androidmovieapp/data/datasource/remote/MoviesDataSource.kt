package com.a.androidmovieapp.data.datasource.remote

import com.a.androidmovieapp.data.common.MoviesResult
import com.a.androidmovieapp.data.response.CharacterResponse
import com.a.androidmovieapp.data.response.MovieResponse
import com.a.androidmovieapp.data.response.PlanetResponse
import com.a.androidmovieapp.data.response.SpecieResponse
import com.a.androidmovieapp.domain.models.PlanetModel
import com.a.androidmovieapp.domain.models.SpecieModel

interface MoviesDataSource {
    suspend fun searchCharacters(input: String): MoviesResult<List<CharacterResponse>?>
        suspend fun getPlanet(planetUrl: String): MoviesResult<PlanetResponse?>
        suspend fun getSpecies(speciesUrl: List<String>): MoviesResult<List<SpecieResponse?>>
        suspend fun getMovies(moviesUrl: List<String>): MoviesResult<List<MovieResponse?>>
}