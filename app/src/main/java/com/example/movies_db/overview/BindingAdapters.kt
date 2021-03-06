package com.example.movies_db.overview

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movies_db.R
import com.example.movies_db.network.MoviesPhoto


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl.let {
        val imgUri = imgUrl?.toUri()?.buildUpon()?.build()
        Glide.with(imgView)
            .load("https://image.tmdb.org/t/p/w500${imgUri}")
//            .override(200, 150)
            .centerCrop() // scale image to fill the entire ImageView
            .error(R.drawable.ic_connection_error)
            .placeholder(R.drawable.ic_broken_image)
            .into(imgView)

    }
}

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<MoviesPhoto>?
) {
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)

}

@BindingAdapter("moviesApiStatus")
fun bindStatus(
    statusImageView: ImageView,
    status: MoviesApiStatus?
) {

    when (status) {

        MoviesApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }

        MoviesApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        MoviesApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }

}

@BindingAdapter("moviesApiStars")
fun bindStar( statusImageView: ImageView,
              status: MoviesApiStars?
) {
    when(status){
        MoviesApiStars.NINE -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.starnine)
        }

        MoviesApiStars.SEVEN -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.starsseven)
        }

        MoviesApiStars.FIVE -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.starfive)
        }
    }
}

//@BindingAdapter("moviesApiGenreID")
//fun bindGenre(genreTextView : TextView,
//status : MoviesApiGenreId?){
//    when(status){
//        MoviesApiGenreId.ACTION -> {
//            genreTextView.visibility = View.VISIBLE
//            genreTextView.setText("ACTION")
//        }
//        MoviesApiGenreId.ANIMATION -> {
//            genreTextView.visibility = View.VISIBLE
//            genreTextView.setText("ANIMATION")
//        }
//    }
//}

