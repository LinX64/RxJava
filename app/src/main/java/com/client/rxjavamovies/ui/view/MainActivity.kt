package com.client.rxjavamovies.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.client.rxjavamovies.databinding.ActivityMainBinding
import com.client.rxjavamovies.ui.MainViewModel
import com.client.rxjavamovies.ui.view.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        observeMovieList()
        observeError()
    }

    private fun setupUI() {
        binding.recyclerview.adapter = adapter

        mainViewModel.getAllMovies()
    }

    private fun observeError() {
        mainViewModel.errorMessage.observe(this, Observer {
            Log.d(TAG, "errorMessage: $it")
        })
    }

    private fun observeMovieList() {
        mainViewModel.movieList.observe(this, Observer {
            if (it != null) {
                adapter.setMovieList(it)
            } else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroy() {
        mainViewModel.disposable.dispose()
        super.onDestroy()
    }
}