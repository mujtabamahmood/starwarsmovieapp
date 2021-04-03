package com.a.androidmovieapp.data.repository

import com.a.androidmovieapp.data.common.MoviesResult
import com.a.androidmovieapp.data.common.onFlowStarts
import com.a.androidmovieapp.data.datasource.remote.MoviesDataSource
import com.a.androidmovieapp.domain.models.CharacterModel
import com.a.androidmovieapp.domain.models.MovieModel
import com.a.androidmovieapp.domain.models.PlanetModel
import com.a.androidmovieapp.domain.models.SpecieModel
import com.a.androidmovieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(private val moviesDataSource: MoviesDataSource) : MoviesRepository {
    override suspend fun searchCharacters(input: String): Flow<MoviesResult<List<CharacterModel>>> =
        flow {
            moviesDataSource.searchCharacters(input).run {
                when (this) {
                    is MoviesResult.Success -> {
                        data?.let { emit(MoviesResult.Success(data.map { it.mapToDomainModel() })) }
                    }
                    is MoviesResult.Error -> {
                        emit(MoviesResult.Error(exception))
                    }
                }
            }
        }.onFlowStarts()


    override suspend fun getPlanet(planetUrl: String): Flow<MoviesResult<PlanetModel>> =
        flow {
            moviesDataSource.getPlanet(planetUrl).run {
                when (this) {
                    is MoviesResult.Success -> {
                        data?.let { emit(MoviesResult.Success(it.mapToDomainModel())) }
                    }
                    is MoviesResult.Error -> {
                        emit(MoviesResult.Error(exception))
                    }
                }
            }
        }.onFlowStarts()

    override suspend fun getSpecies(speciesUrl: List<String>): Flow<MoviesResult<List<SpecieModel>>> =
        flow {
            moviesDataSource.getSpecies(speciesUrl).run {
                when (this) {
                    is MoviesResult.Success -> {
                        data.let { emit(MoviesResult.Success(data.map { specie -> specie!!.mapToDomainModel() })) }
                    }
                    is MoviesResult.Error -> {
                        emit(MoviesResult.Error(exception))
                    }
                }
            }
        }.onFlowStarts()


    override suspend fun getMovies(movieUrl: List<String>): Flow<MoviesResult<List<MovieModel>>> =
        flow {
            moviesDataSource.getMovies(movieUrl).run {
                when (this) {
                    is MoviesResult.Success -> {
                        emit(MoviesResult.Success(data.map { movie -> movie!!.mapToDomainModel() }))
                    }
                    is MoviesResult.Error -> {
                        emit(MoviesResult.Error(exception))
                    }
                }
            }
        }.onFlowStarts()


}