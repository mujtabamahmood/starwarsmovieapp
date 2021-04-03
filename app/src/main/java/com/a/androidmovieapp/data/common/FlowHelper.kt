package com.a.androidmovieapp.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart

fun <T> Flow<MoviesResult<T>>.onFlowStarts() =
    onStart {
        emit(MoviesResult.Loading)
    }.catch { emit(MoviesResult.Error(DataSourceException.Unexpected(2))) }