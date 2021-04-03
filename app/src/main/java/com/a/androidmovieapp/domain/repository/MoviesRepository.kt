package com.a.androidmovieapp.domain.repository

import com.a.androidmovieapp.data.common.MoviesResult
import com.a.androidmovieapp.domain.models.CharacterModel
import com.a.androidmovieapp.domain.models.MovieModel
import com.a.androidmovieapp.domain.models.PlanetModel
import com.a.androidmovieapp.domain.models.SpecieModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun searchCharacters(input: String): Flow<MoviesResult<List<CharacterModel>>>
    suspend fun getPlanet(planetUrl: String): Flow<MoviesResult<PlanetModel>>
    suspend fun getSpecies(speciesUrl: List<String>): Flow<MoviesResult<List<SpecieModel>>>
    suspend fun getMovies(movieUrl: List<String>): Flow<MoviesResult<List<MovieModel>>>
}