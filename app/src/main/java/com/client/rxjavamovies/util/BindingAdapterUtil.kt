package com.client.rxjavamovies.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.client.rxjavamovies.R

object BindingAdapterUtil {

    @BindingAdapter("loadImage")
    @JvmStatic
    fun ImageView.loadImageFromUrl(url: String) {
        Glide.with(this.context).load(url).into(this)
    }
}