package com.example.movies_db.network


import com.example.movies_db.overview.MoviesApiGenreId
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MoviesApiService {

    @GET("popular?api_key=112ceb868d6eaf3d809274a1b6559874")
    suspend fun getPhotos(@Query("page") pageNo : Int): ResultOfMoviesPhotots

    @GET("popular?api_key=112ceb868d6eaf3d809274a1b6559874")
    suspend fun getMoviesWithFilter(@Query("with_genres",)genreId: Int , @Query("page") pageNo : Int) : Response

//    @GET("popular?api_key=112ceb868d6eaf3d809274a1b6559874")
//    suspend fun getGenreId() : MoviesPhoto


//    @GET("https://api.themoviedb.org/3/movie/popular?api_key=${API_KEY}&language=en=US")
//    suspend fun getAllPopMovies(@Query("page") page : Int) : GetMovieDbRes
}

object MoviesApi {
    val retrofitService: MoviesApiService by lazy {
        retrofit.create(MoviesApiService::class.java)
    }
}

