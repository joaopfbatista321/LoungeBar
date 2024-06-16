package com.appbar.myapplication.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class User(val username: String, val password: String)

interface ApiService {
    @POST("/register")
    fun register(@Body user: User): Call<String>

    @POST("/login")
    fun login(@Body user: User): Call<String>
}