package com.example.starwars.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Like {
    @SerializedName("planet_id")
    @Expose
    var planetId: Int? = null
    @SerializedName("likes ")
    @Expose
    var likes: Int? = null

    override fun toString(): String {
        return "planetId: $planetId, $likes likes."
    }
}