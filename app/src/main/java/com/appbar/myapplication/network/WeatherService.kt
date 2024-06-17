package com.appbar.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
// Interface definindo o serviço de API para obter dados de clima usando Retrofit
interface WeatherService {
    // Solicitação GET para obter o clima atual
    // O endpoint é "current.json"
    @GET("current.json")
    fun getCurrentWeather(
        @Query("key") apiKey: String,// Chave da API passada como parâmetro de consulta (query parameter)
        @Query("q") city: String// Cidade passada como parâmetro de consulta (query parameter)
    ): Call<WeatherResponse>// Retorna uma chamada que resultará em um WeatherResponse
}

