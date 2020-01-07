package com.example.starwars.ui.dashboard.residents.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.starwars.api.Api
import com.example.starwars.api.PlanetService
import com.example.starwars.api.Resident
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResidentDetailViewModel : ViewModel() {
    var liveResident = MutableLiveData<Resident?>()

    fun getResident(id: String?) {
        Api.instance?.create(PlanetService::class.java)?.getResident(id)?.enqueue(object : Callback<Resident?> {
            override fun onResponse(call: Call<Resident?>, response: Response<Resident?>) {
                liveResident.value = response.body()
            }

            override fun onFailure(call: Call<Resident?>, t: Throwable) {
                Log.e("Failure ", "" + t.localizedMessage)
            }
        })
    }
}
