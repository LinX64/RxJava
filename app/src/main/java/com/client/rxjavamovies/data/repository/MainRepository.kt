package com.client.rxjavamovies.data.repository

import com.client.rxjavamovies.data.api.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val retrofitService: ApiService) {
    fun getAllMovies() = retrofitService.getAllMovies()
}