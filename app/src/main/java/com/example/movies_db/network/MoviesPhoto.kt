package com.example.movies_db.network

import com.squareup.moshi.Json




data class ResultOfMoviesPhotots(val results : List<MoviesPhoto> )


data class MoviesPhoto(
    val id : String ,
    @Json (name = "title") val title : String ,
    @Json (name = "poster_path") val postre : String
    )


