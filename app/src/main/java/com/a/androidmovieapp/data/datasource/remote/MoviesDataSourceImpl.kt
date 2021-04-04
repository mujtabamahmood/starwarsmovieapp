package com.a.androidmovieapp.data.datasource.remote

import com.a.androidmovieapp.R
import com.a.androidmovieapp.data.common.DataSourceException
import com.a.androidmovieapp.data.common.MoviesResult
import com.a.androidmovieapp.data.common.RequestErrorHandler
import com.a.androidmovieapp.data.common.asyncAll
import com.a.androidmovieapp.data.common.datasource.remote.MoviesServices
import com.a.androidmovieapp.data.response.CharacterResponse
import com.a.androidmovieapp.data.response.MovieResponse
import com.a.androidmovieapp.data.response.PlanetResponse
import com.a.androidmovieapp.data.response.SpecieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class MoviesDataSourceImpl(private val moviesApi: MoviesServices) : MoviesDataSource {
    override suspend fun searchCharacters(input: String): MoviesResult<List<CharacterResponse>?> {
        return try {
            val result = moviesApi.searchCharacter(input)
            if (result.isSuccessful) {
                MoviesResult.Success(result.body()?.results)
            } else {
                MoviesResult.Error(DataSourceException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            MoviesResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getPlanet(planetUrl: String): MoviesResult<PlanetResponse?> {
        return try {
            val result = moviesApi.getPlanet(planetUrl)
            if (result.isSuccessful)
                MoviesResult.Success(result.body())
            else
                MoviesResult.Error(DataSourceException.Server(result.errorBody()))
        } catch (e: Exception) {
            MoviesResult.Error(RequestErrorHandler.getRequestError(e))

        }
    }

    override suspend fun getSpecies(speciesUrl: List<String>): MoviesResult<List<SpecieResponse?>> {
        return try {
            val species = ArrayList<SpecieResponse?>()
            withContext(Dispatchers.IO) {
                asyncAll(speciesUrl) { moviesApi.getSpecies(it) }
                    .awaitAll()
                    .forEach {
                        if (it.isSuccessful) {
                            species.add(it.body())
                        }
                    }
            }
            if (species.isNotEmpty()) {
                MoviesResult.Success(species)
            } else {
                MoviesResult.Error(DataSourceException.Server(R.string.unexpected_error_message))
            }
        } catch (e: Exception) {
            MoviesResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }


    override suspend fun getMovies(moviesUrl: List<String>): MoviesResult<List<MovieResponse?>> {
        return try {
            val movies = ArrayList<MovieResponse?>()
            withContext(Dispatchers.IO) {
                asyncAll(moviesUrl) { moviesApi.getMovies(it) }
                    .awaitAll()
                    .forEach {
                        if (it.isSuccessful) {
                            movies.add(it.body())
                        }
                    }
            }
            if (movies.isNotEmpty()) {
                MoviesResult.Success(movies)
            } else {
                MoviesResult.Error(DataSourceException.Server(R.string.unexpected_error_message))
            }
        } catch (e: Exception) {
            MoviesResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }
}