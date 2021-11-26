package com.example.movies_db.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_db.R
import com.example.movies_db.databinding.GridViewItemBinding
import com.example.movies_db.network.MoviesPhoto

class PhotoGridAdapter : ListAdapter<MoviesPhoto,
        PhotoGridAdapter.MoviesPhotoViewHolder>(DiffCallback) {


        class MoviesPhotoViewHolder(private var binding:
                                      GridViewItemBinding
        ):
            RecyclerView.ViewHolder(binding.root) {
            fun bind(moviesPhoto: MoviesPhoto) {
                binding.result = moviesPhoto
                binding.executePendingBindings()
            }
            val button : Button = binding.button
        }

        companion object DiffCallback : DiffUtil.ItemCallback<MoviesPhoto>() {
            override fun areItemsTheSame(oldItem: MoviesPhoto, newItem: MoviesPhoto): Boolean {
                return newItem.title == oldItem.title
            }

            override fun areContentsTheSame(oldItem: MoviesPhoto, newItem: MoviesPhoto): Boolean {
                return oldItem.posterPath == newItem.posterPath
            }
        }


        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): MoviesPhotoViewHolder {
            return MoviesPhotoViewHolder(GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)))    }



        override fun onBindViewHolder(holder: MoviesPhotoViewHolder, position: Int) {
            val marsPhoto = getItem(position)
            holder.bind(marsPhoto)

            holder.button.setOnClickListener {

//                    val action = moviesFragmentDirections.action_moviesFragment_to_detailsFragment()
//                    holder.button.findNavController().navigate(action)

                }



        }

//    override fun onBindViewHolder(holder: MoviesPhotoViewHolder, position: Int) {
//        val marsPhoto = getItem(position)
//        holder.bind(marsPhoto)
//
//        holder.button.setOnClickListener {
////            val action =
//
//            val action = MoviesFragmentDirection.action_moviesFragment_to_detailsFragment()
//            holder.button.findNavController().navigate(action)
//
//        }
//    }
    }

