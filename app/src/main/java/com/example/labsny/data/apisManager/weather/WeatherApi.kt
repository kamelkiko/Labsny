package com.example.labsny.data.apisManager.weather

import com.example.labsny.data.apisManager.ApiRequest
import com.example.labsny.data.models.request.WeatherRequest
import com.example.labsny.data.models.response.WeatherResponse
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

class WeatherApi : ApiRequest(), IWeatherApi {
    private val client = OkHttpClient.Builder().addInterceptor(logInterceptor).build()
    override fun getTodayWeather(
        weatherRequest: WeatherRequest,
        onSuccess: (WeatherResponse) -> Unit,
        onFailed: (IOException) -> Unit,
    ) {
        val request = getRequest(weatherRequest.location, weatherRequest.apikey)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                onFailed(e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string()?.let { jsonString ->
                        val weatherResponse =
                            gson.fromJson(jsonString, WeatherResponse::class.java)
                        onSuccess(weatherResponse)
                    }
                } else
                    onFailed(IOException("Not found your city!"))
            }

        })
    }
}