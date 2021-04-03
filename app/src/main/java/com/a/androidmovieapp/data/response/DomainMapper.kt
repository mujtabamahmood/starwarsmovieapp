package com.a.androidmovieapp.data.response

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}