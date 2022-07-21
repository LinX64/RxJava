package com.client.rxjavamovies.data.api.model

import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Movie(
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String
)