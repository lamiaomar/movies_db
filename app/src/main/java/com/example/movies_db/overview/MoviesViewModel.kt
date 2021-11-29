package com.example.movies_db.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_db.network.MoviesApi
import com.example.movies_db.network.MoviesPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

enum class MoviesApiStatus { LOADING, ERROR, DONE }

enum class MoviesApiStars { NINE, SEVEN, FIVE }
class MoviesViewModel : ViewModel() {

    private var _currentPosition = MutableLiveData<Int?>()
    val currentPosition
        get() = _currentPosition

    private val _results = MutableLiveData<List<MoviesPhoto>>()
    val results: LiveData<List<MoviesPhoto>> = _results

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _postre = MutableLiveData<String>()
    val poster: LiveData<String> = _postre

    var overview = MutableLiveData<String?>()

    var origina_language = MutableLiveData<String?>()

    var backdrop_path = MutableLiveData<String?>()

    var release_date = MutableLiveData<String?>()

    var popularity = MutableLiveData<String?>()

    var vote_average = MutableLiveData<Double?>()

    var vote_count = MutableLiveData<String?>()

    var _star = MutableLiveData<MoviesApiStars>()
    val star: LiveData<MoviesApiStars> = _star

    private val _status = MutableLiveData<MoviesApiStatus>()
    val status: LiveData<MoviesApiStatus> = _status

    var favoriteList = mutableListOf<String>()

    init {
        getMoviesPhotos()
    }


    private fun getMoviesPhotos() {

        _status.value = MoviesApiStatus.LOADING
        viewModelScope.launch {
            try {
                _results.value = MoviesApi.retrofitService.getPhotos()
                    .results.sortedBy { it.originalTitle }
//                    .filter { it.adult }
//                _results.value!!.sortedBy { it.originalTitle }

                _title.value = _results.value!![0].title
                _postre.value = _results.value!![0].posterPath

                _status.value = MoviesApiStatus.DONE
            } catch (e: Exception) {
                _status.value = MoviesApiStatus.ERROR
                _results.value = listOf()
            }

        }
    }

    //        Log.e("TAG","oveview:${overview.value}")

    fun displayMovieDescription(displayPosition: Int) {

        val item = results.value?.get(displayPosition)

        _postre.value = item?.posterPath
        overview.value = item?.overview
        _title.value = item?.title
        origina_language.value = item?.originalLanguage
        backdrop_path.value = item?.backdropPath
        release_date.value = item?.releaseDate

        popularity.value = item?.popularity.toString()
        vote_average.value = item?.voteAverage
        vote_count.value = item?.voteCount.toString()

        if (vote_average.value!! > 8.0) {
            _star.value = MoviesApiStars.NINE
        } else if (vote_average.value!! > 6.0) {
            _star.value = MoviesApiStars.SEVEN
        } else if (vote_average.value!! > 4.0) {
            _star.value = MoviesApiStars.FIVE
        }
    }


}