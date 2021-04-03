package com.a.androidmovieapp.data.response

import com.a.androidmovieapp.domain.models.MovieModel
import com.a.androidmovieapp.utils.UNDEFINED
import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("title") val title: String?,
    @SerializedName("opening_crawl") val description: String?
) : DomainMapper<MovieModel> {
    override fun mapToDomainModel(): MovieModel =
        MovieModel(title ?: UNDEFINED, description ?: UNDEFINED, false)
}
