package com.a.androidmovieapp.domain.usecases

import com.a.androidmovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

open class GetMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
    ) {
    suspend operator fun invoke(movieUrl: List<String>) = moviesRepository.getMovies(movieUrl)
}