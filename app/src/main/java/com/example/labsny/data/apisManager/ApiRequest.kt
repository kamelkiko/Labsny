package com.example.labsny.data.apisManager

import com.example.labsny.util.Constants
import com.google.gson.Gson
import okhttp3.HttpUrl
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

abstract class ApiRequest {
    val gson = Gson()
    val logInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private fun makeUrl(location: String, apiKey: String) =
        HttpUrl.Builder().scheme(SCHEME)
            .host(Constants.BASE_URL)
            .addPathSegment(VERSION_PATH_SEGMENT)
            .addPathSegment(TIME_LINES_PATH_SEGMENT)
            .addQueryParameter("location", location)
            .addQueryParameter("fields", FIELDS)
            .addQueryParameter("timesteps", TIME_STEPS)
            .addQueryParameter("units", UNITS)
            .addQueryParameter("apikey", apiKey)
            .build()

    private fun makeRequest(location: String, apiKey: String): Request.Builder = Request.Builder()
        .url(makeUrl(location, apiKey))

    open fun getRequest(location: String, apiKey: String) =
        makeRequest(location, apiKey).get().build()

    companion object {
        private const val FIELDS = "temperature,humidity,windSpeed,cloudCover,weatherCode"
        private const val TIME_STEPS = "current"
        private const val UNITS = "metric"
        private const val SCHEME = "https"
        private const val VERSION_PATH_SEGMENT = "v4"
        private const val TIME_LINES_PATH_SEGMENT = "timelines"
    }
}