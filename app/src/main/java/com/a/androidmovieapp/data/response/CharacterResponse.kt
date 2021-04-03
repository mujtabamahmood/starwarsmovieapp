package com.a.androidmovieapp.data.response

import com.a.androidmovieapp.domain.models.CharacterModel
import com.a.androidmovieapp.utils.UNDEFINED
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("birth_year") val birthYear: String?,
    @SerializedName("height") val height: String?,
    @SerializedName("homeworld") val homeWorld: String?,
    @SerializedName("films") val films: List<String>?,
    @SerializedName("species") val species: List<String>?,

    ) : DomainMapper<CharacterModel> {
    override fun mapToDomainModel() = CharacterModel(
    name?: UNDEFINED,
    birthYear ?: UNDEFINED,
    height  ?: UNDEFINED,
    homeWorld ?: UNDEFINED,
    films ?: emptyList(),
    species ?: emptyList()

    )
}
