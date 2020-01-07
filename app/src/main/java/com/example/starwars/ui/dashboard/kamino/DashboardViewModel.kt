package com.example.starwars.ui.dashboard.kamino

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwars.api.Api
import com.example.starwars.api.Like
import com.example.starwars.api.Planet
import com.example.starwars.api.PlanetService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    var residents = MutableLiveData<List<String>>()
    var livePlanet = MutableLiveData<Planet>()
    var imageUrl = MutableLiveData<String>()
    var liveLike = MutableLiveData<Like?>()

    var message = MutableLiveData<CharSequence>()

    fun publish(list: List<String>) {
        residents.value = list
    }

    val planet: Unit
        get() {
            Api.instance?.create(PlanetService::class.java)?.planet?.enqueue(object : Callback<Planet?> {
                override fun onResponse(call: Call<Planet?>, response: Response<Planet?>) {
                    val planet = response.body()

                    if (planet != null) {
                        livePlanet.postValue(planet)
                        imageUrl.postValue(planet.imageUrl)
                    } else {
                        message.postValue(response.message())
                    }
                }

                override fun onFailure(call: Call<Planet?>, t: Throwable) {
                    message.postValue(t.localizedMessage)
                }
            })
        }

    fun like() {
        Api.instance?.create(PlanetService::class.java)?.like()?.enqueue(object : Callback<Like?> {
            override fun onFailure(call: Call<Like?>, t: Throwable) {
                message.postValue(t.localizedMessage)
            }

            override fun onResponse(call: Call<Like?>, response: Response<Like?>) {
                liveLike.postValue(response.body())
            }
        })
    }
}