package com.a.androidmovieapp.domain.usecases

import com.a.androidmovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

open class SearchCharactersUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(input: String) = moviesRepository.searchCharacters(input)
}