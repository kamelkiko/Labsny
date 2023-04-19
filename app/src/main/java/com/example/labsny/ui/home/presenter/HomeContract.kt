package com.example.labsny.ui.home.presenter

import com.example.labsny.data.models.request.WeatherRequest
import com.example.labsny.data.models.response.WeatherResponse
import java.io.IOException

interface HomeContract {
    interface IView {
        fun showData(weatherResponse: WeatherResponse.Data.Timeline.Interval.Values)
        fun showError(error: IOException)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface IPresenter {
        fun fetchData(weatherRequest: WeatherRequest)
    }
}