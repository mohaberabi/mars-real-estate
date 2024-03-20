package com.example.marsrealestate

import android.view.View
import android.widget.ImageView
import android.widget.ListAdapter
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.marsrealestate.listing.ListingAdapter
import com.example.marsrealestate.listing.ListingViewModel
import com.example.marsrealestate.listing.MarsApiStatus
import com.example.marsrealestate.network.MarsProperty

@BindingAdapter("listData")
fun bindRecView(recycler: RecyclerView, data: List<MarsProperty>?) {
    val adpater = recycler.adapter as? ListingAdapter
    adpater?.submitList(data)
}

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView, status: MarsApiStatus?) {
    when (status) {
        MarsApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        MarsApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }

        else -> {
            statusImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl")
fun bindNetWorkImage(imgView: ImageView, url: String?) {
    url?.let {
        val imageUrl = url.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context).load(imageUrl)
            .apply(RequestOptions())
            .placeholder(R.drawable.loading_img)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}