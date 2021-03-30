package com.app.marveltestapp.model.entity

import com.google.gson.annotations.SerializedName

data class CharactersDataResponse(
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: Data,
) {
    data class Data(
        @SerializedName("count")
        val count: Int,
        @SerializedName("limit")
        val limit: Int,
        @SerializedName("offset")
        val offset: Int,
        @SerializedName("results")
        val results: List<Result>,
        @SerializedName("total")
        val total: Int
    ) {
        data class Result(
            @SerializedName("description")
            val description: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("modified")
            val modified: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("resourceURI")
            val resourceURI: String,
            @SerializedName("thumbnail")
            val thumbnail: Thumbnail
        ) {
            data class Thumbnail(
                @SerializedName("extension")
                val extension: String,
                @SerializedName("path")
                val path: String
            )
        }
    }
}