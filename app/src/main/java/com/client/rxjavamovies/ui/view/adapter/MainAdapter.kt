package com.client.rxjavamovies.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.client.rxjavamovies.data.api.model.Movie
import com.client.rxjavamovies.databinding.ListViewItemBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    private var movies = mutableListOf<Movie>()

    inner class MyViewHolder(
        private val binding: ListViewItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myMovies: Movie) {
            binding.myMovies = myMovies
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemBinding =
            ListViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun setMovieList(movies: List<Movie>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }
}