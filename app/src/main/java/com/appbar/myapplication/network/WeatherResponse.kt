package com.appbar.myapplication.network

data class WeatherResponse(val current: Current) {
    data class Current(
        val temp_c: Float,
        val condition: Condition,
        val humidity: Int,
        val wind_kph: Float
    )

    data class Condition(val text: String)
}

