package com.example.labsny.ui.home.presenter

import com.example.labsny.data.apisManager.weather.IWeatherApi
import com.example.labsny.data.models.request.WeatherRequest
import com.example.labsny.data.models.response.WeatherResponse
import java.io.IOException

class HomePresenter(private val view: HomeContract.IView, private val weatherApi: IWeatherApi) :
    HomeContract.IPresenter {
    override fun fetchData(weatherRequest: WeatherRequest) {
        weatherApi.getTodayWeather(
            weatherRequest,
            ::onSuccess,
            ::onFailed
        )
    }

    private fun onSuccess(weatherResponse: WeatherResponse) {
        view.showData(weatherResponse.data.timelines[0].intervals[0].values)
        view.showProgressBar()
    }

    private fun onFailed(ioException: IOException) {
        view.showError(ioException)
        view.hideProgressBar()
    }
}