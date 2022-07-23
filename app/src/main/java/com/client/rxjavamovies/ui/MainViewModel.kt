package com.client.rxjavamovies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.client.rxjavamovies.data.api.model.Movie
import com.client.rxjavamovies.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    val loading = MutableLiveData<Boolean>()

    fun getAllMovies() {
        loading.postValue(true)
        repository.getAllMovies().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    loading.postValue(false)
                }, onError = { t ->
                    errorMessage.postValue(t.message.toString())
                    loading.postValue(false)
                }, onNext = { movies ->
                    movieList.postValue(movies.movieList)
                }
            )
    }
}