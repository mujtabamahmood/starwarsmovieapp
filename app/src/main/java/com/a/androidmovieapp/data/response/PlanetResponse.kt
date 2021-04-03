package com.a.androidmovieapp.data.response

import com.a.androidmovieapp.domain.models.PlanetModel
import com.a.androidmovieapp.utils.UNDEFINED
import com.google.gson.annotations.SerializedName

data class PlanetResponse(@SerializedName("population") val population: String?) :
    DomainMapper<PlanetModel> {
    override fun mapToDomainModel(): PlanetModel = PlanetModel(population ?: UNDEFINED)
}
