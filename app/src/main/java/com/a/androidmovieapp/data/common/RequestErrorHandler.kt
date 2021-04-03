package com.a.androidmovieapp.data.common

import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object RequestErrorHandler {
    private const val CLIENT_START_HTTP_CODE = 400
    private const val CLIENT_END_HTTP_CODE = 499
    private const val SERVER_START_HTTP_CODE = 500
    private const val SERVER_END_HTTP_CODE = 599

    fun getRequestError(throwable: Throwable): DataSourceException{
        return when (throwable){
            is HttpException -> {
                handleHttpException(throwable)
            }
            is IOException -> {
                DataSourceException.Connection(1)
            }

            is SocketTimeoutException -> {
                DataSourceException.Timeout(1)
            }
            else -> {
                DataSourceException.Unexpected(1)
            }
        }
    }

    private fun handleHttpException(httpException: HttpException): DataSourceException {
        return when (httpException.code()){
            in CLIENT_START_HTTP_CODE..CLIENT_END_HTTP_CODE -> {
                DataSourceException.Client(1)

            }
            in SERVER_START_HTTP_CODE..SERVER_END_HTTP_CODE ->{
                DataSourceException.Server("server error")
            }

            else -> {
                DataSourceException.Unexpected(1)
            }
        }
    }

}