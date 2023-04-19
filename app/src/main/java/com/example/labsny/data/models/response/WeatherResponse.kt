package com.example.labsny.data.models.response

data class WeatherResponse(
    val data: Data = Data()
) {
    data class Data(
        val timelines: List<Timeline> = listOf()
    ) {
        data class Timeline(
            val endTime: String = "",
            val intervals: List<Interval> = listOf(),
            val startTime: String = "",
            val timestep: String = ""
        ) {
            data class Interval(
                val startTime: String = "",
                val values: Values = Values()
            ) {
                data class Values(
                    val cloudCover: Int = 0,
                    val humidity: Int = 0,
                    val temperature: Double = 0.0,
                    val weatherCode: Int = 0,
                    val windSpeed: Double = 0.0
                )
            }
        }
    }
}