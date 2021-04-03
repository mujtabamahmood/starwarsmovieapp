package com.a.androidmovieapp.data.response

import com.google.gson.annotations.SerializedName

data class ListCharacterResponse(
    @SerializedName("results") val results: List<CharacterResponse>?
)