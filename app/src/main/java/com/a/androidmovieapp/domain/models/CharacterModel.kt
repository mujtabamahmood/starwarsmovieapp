package com.a.androidmovieapp.domain.models

import android.os.Parcelable
import com.a.androidmovieapp.convertUrlToHttps
import kotlinx.parcelize.Parcelize


@Parcelize
data class CharacterModel(
    val name: String,
    val birthYear: String,
    val height: String,
    val homeWorld: String,
    val films: List<String>,
    val species: List<String>
) : Parcelable {
    fun getPlanetUrl() = homeWorld.convertUrlToHttps()
    fun getMoviesUrl() = films.map { it.convertUrlToHttps() }
    fun getSpeciesUrl() = species.map { it.convertUrlToHttps() }
}
