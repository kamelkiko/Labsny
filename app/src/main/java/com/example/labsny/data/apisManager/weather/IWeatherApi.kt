package com.example.labsny.data.apisManager.weather

import com.example.labsny.data.models.response.WeatherResponse
import com.example.labsny.data.models.request.WeatherRequest
import java.io.IOException

interface IWeatherApi {
    fun getTodayWeather(
        weatherRequest: WeatherRequest,
        onSuccess: (WeatherResponse) -> Unit,
        onFailed: (IOException) -> Unit,
    )

}
