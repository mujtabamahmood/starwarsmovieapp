package com.a.androidmovieapp.domain.usecases

import com.a.androidmovieapp.domain.repository.MoviesRepository
import javax.inject.Inject

open class GetSpeciesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(specieUrl: List<String>) = moviesRepository.getSpecies(specieUrl)
}