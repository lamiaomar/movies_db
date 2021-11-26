package com.example.movies_db.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_db.network.MoviesApi
import com.example.movies_db.network.MoviesPhoto
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesViewModel : ViewModel() {

    private val _results = MutableLiveData<List<MoviesPhoto>>()
    val results: LiveData<List<MoviesPhoto>> = _results

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> = _title

    private val _postre = MutableLiveData<String>()
    val poster: LiveData<String> = _postre

    private val _overview = MutableLiveData<String>()
    val overview: LiveData<String> = _overview

    init {
        getMoviesPhotos()
    }


    private fun getMoviesPhotos() {

        viewModelScope.launch {
            try {
                _results.value = MoviesApi.retrofitService.getPhotos().results
                _title.value = _results.value!![0].title
                _postre.value = _results.value!![0].posterPath
                _overview.value = _results.value!![0].overview

            } catch (e: Exception) {
                _title.value = "Failure : ${e.message}"
            }

        }
    }

    fun goto(){

    }
}