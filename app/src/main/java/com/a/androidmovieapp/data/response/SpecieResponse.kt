package com.a.androidmovieapp.data.response

import com.a.androidmovieapp.domain.models.SpecieModel
import com.a.androidmovieapp.utils.UNDEFINED
import com.google.gson.annotations.SerializedName

data class SpecieResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("language") val language: String?
) : DomainMapper<SpecieModel> {
    override fun mapToDomainModel(): SpecieModel =
        SpecieModel(name ?: UNDEFINED, language ?: UNDEFINED)
}
