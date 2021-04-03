package com.a.androidmovieapp.data.common

sealed class MoviesResult<out T> {
    data class Success<out T>(val data: T) : MoviesResult<T>()
    data class Error(val exception: DataSourceException) : MoviesResult<Nothing>()
    object Loading : MoviesResult<Nothing>()
}

inline fun <T: Any> MoviesResult<T>.onSuccess(action: (T) -> Unit): MoviesResult<T> {
    if (this is MoviesResult.Success) action(data)
    return this
}

inline fun <T : Any> MoviesResult<T>.onError(action: (DataSourceException) -> Unit): MoviesResult<T> {
    if (this is MoviesResult.Error) action(exception)
    return this
}

inline fun <T : Any> MoviesResult<T>.onLoading(action: () -> Unit): MoviesResult<T> {
    if (this is MoviesResult.Loading) action()
    return this
}
