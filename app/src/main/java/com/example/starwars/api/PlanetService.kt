package com.example.starwars.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlanetService {
    @get:GET("/planets/10")
    val planet: Call<Planet?>?

    @GET("/residents/{id}")
    fun getResident(@Path("id") id: String?): Call<Resident?>?

    @POST("/planets/10/like")
    fun like(): Call<Like?>?
}