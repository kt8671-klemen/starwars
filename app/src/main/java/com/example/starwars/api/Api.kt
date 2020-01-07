package com.example.starwars.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Api private constructor() {
    private val retrofit: Retrofit.Builder

    fun <S> create(service: Class<S>): S {
        val okHttpClient = OkHttpClient.Builder()
        retrofit.client(okHttpClient.build())
        return retrofit.build().create(service)
    }

    companion object {
        @Volatile
        private var api: Api? = null

        val instance: Api?
            get() {
                if (api == null) {
                    synchronized(lock = Api::class) {
                        if (api == null) {
                            api = Api()
                        }
                    }
                }
                return api
            }
    }

    init {
        retrofit = Retrofit.Builder()
            .baseUrl("https://private-anon-9a3195e124-starwars2.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
    }
}