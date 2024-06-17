package com.appbar.myapplication.network

// Data class representando a resposta da API de clima
data class WeatherResponse(val current: Current) {
    // Data class aninhada representando os dados atuais do clima
    data class Current(
        val temp_c: Float,
        val condition: Condition,
        val humidity: Int,
        val wind_kph: Float
    )

    // Data class aninhada representando a condição do clima
    data class Condition(val text: String)
}

