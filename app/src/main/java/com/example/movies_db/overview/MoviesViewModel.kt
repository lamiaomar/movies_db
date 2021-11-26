package com.example.movies_db.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_db.network.MoviesApi
import kotlinx.coroutines.launch
import java.lang.Exception

class MoviesViewModel :  ViewModel() {

    private val _status = MutableLiveData<String>()

    val status: LiveData<String> = _status

    init {
        getMoviesPhotos()
    }


    private fun getMoviesPhotos() {

        viewModelScope.launch {
            try {
                val listResult = MoviesApi.retrofitService.getPhotos()
                _status.value = "listResult ${listResult.results.size}"
            }
            catch ( e : Exception){
                _status.value = "Failure : ${e.message}"
            }

        }
    }
}