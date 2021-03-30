package com.app.marveltestapp.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterData(

    @SerializedName("id")

    var id: String,

    @SerializedName("name")

    var name: String,

    @SerializedName("img")

    var img: String,

    @SerializedName("description")

    var description: String,

    @SerializedName("lat")

    var lat: Float,

    @SerializedName("lon")

    var lon: Float,

    @SerializedName("www")

    var www: String,

    @SerializedName("phone")

    var phone: String,
)