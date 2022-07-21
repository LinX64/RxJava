package com.client.rxjavamovies.data.api.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class MovieList(@SerializedName("Search") val movieList: List<Movie>)