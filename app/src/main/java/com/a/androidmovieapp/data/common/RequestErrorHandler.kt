package com.a.androidmovieapp.data.common

import com.a.androidmovieapp.R
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
                DataSourceException.Connection(R.string.network_connection_error)
            }

            is SocketTimeoutException -> {
                DataSourceException.Timeout(R.string.network_connection_error)
            }
            else -> {
                DataSourceException.Unexpected(R.string.unexpected_error_message)
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