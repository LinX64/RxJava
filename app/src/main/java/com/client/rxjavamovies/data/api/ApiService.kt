package com.client.rxjavamovies.data.api

import com.client.rxjavamovies.data.api.model.MovieList
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {
    @GET("api?s=batman")
    fun getAllMovies(): Observable<MovieList>
}