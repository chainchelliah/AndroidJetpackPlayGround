package com.sample.test.utils.network

/**
 * Created by SangiliPandian C on 22-11-2019.
 */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val error: String? = "Something went wrong, please try again later"
) {
    companion object {
        //private const val ERROR = "Something went wrong"
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(error: String?, data: T?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                error
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
