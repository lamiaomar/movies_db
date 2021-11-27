package com.example.movies_db

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.movies_db.databinding.FragmentDetailsBinding
import com.example.movies_db.databinding.FragmentMoviesBinding
import com.example.movies_db.overview.MoviesViewModel
import com.example.movies_db.overview.PhotoGridAdapter
import java.text.FieldPosition

private const val position = "position"

class DetailsFragment : Fragment() {

    private var _currentPosition = MutableLiveData<Int>()
    val currentPosition
        get() = _currentPosition


    private val viewModel: MoviesViewModel by activityViewModels()

    private  var displayPosition : Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            displayPosition = it.getInt(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner = this

        // Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currentPosition.value = displayPosition

        viewModel.displayMovieDescription()


    }
}