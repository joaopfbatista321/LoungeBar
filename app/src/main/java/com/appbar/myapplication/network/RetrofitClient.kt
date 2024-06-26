package com.appbar.myapplication.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Objeto Singleton para criar e fornecer uma instância do Retrofit
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3000" // Use 10.0.2.2 para localhost em emulador Android
    // Instância do ApiService criada utilizando Retrofit, inicializada de forma preguiçosa
    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}