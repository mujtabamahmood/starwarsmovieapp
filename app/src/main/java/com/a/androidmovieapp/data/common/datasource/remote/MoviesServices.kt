package com.a.androidmovieapp.data.common.datasource.remote

import com.a.androidmovieapp.data.common.GET_SEARCH_CHARACTERS_URL
import com.a.androidmovieapp.data.response.ListCharacterResponse
import com.a.androidmovieapp.data.response.MovieResponse
import com.a.androidmovieapp.data.response.PlanetResponse
import com.a.androidmovieapp.data.response.SpecieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface MoviesServices {
    @GET(GET_SEARCH_CHARACTERS_URL)
    suspend fun searchCharacter(@Query("search") input: String): Response<ListCharacterResponse>

    @GET
    suspend fun getPlanet(@Url planetUrl:String): Response<PlanetResponse>

    @GET
    suspend fun getSpecies(@Url specieUrl: String): Response<SpecieResponse>

    @GET
    suspend fun getMovies(@Url moviesUrl: String): Response<MovieResponse>
}