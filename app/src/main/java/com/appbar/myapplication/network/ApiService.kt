package com.appbar.myapplication.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

// Data class representando um Usuário com nome de usuário e senha
data class User(val username: String, val password: String)

// Interface definindo o serviço de API com anotações do Retrofit
interface ApiService {
    @POST("/register")
    fun register(@Body user: User): Call<String>

    @POST("/login")
    fun login(@Body user: User): Call<String>
}