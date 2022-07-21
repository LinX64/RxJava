package com.client.rxjavamovies.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.client.rxjavamovies.data.api.model.Movie
import com.client.rxjavamovies.data.api.model.MovieList
import com.client.rxjavamovies.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {
    val movieList = MutableLiveData<List<Movie>>()
    val errorMessage = MutableLiveData<String>()
    lateinit var disposable: Disposable

    fun getAllMovies() {
        val response = repository.getAllMovies()
        response.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(getMoviesListObserver())
    }

    private fun getMoviesListObserver(): Observer<MovieList> {
        return object : Observer<MovieList> {
            override fun onComplete() {
                //hide progress indicator .
            }

            override fun onError(e: Throwable) {
                movieList.postValue(null)
            }

            override fun onNext(movies: MovieList) {
                movieList.postValue(movies.movieList)
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
                //start showing progress indicator.
            }
        }
    }
}