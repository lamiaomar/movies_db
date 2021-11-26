package com.example.movies_db.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movies_db.databinding.GridViewItemBinding
import com.example.movies_db.network.MoviesPhoto

class PhotoGridAdapter : ListAdapter<MoviesPhoto,
        PhotoGridAdapter.MoviesPhotoViewHolder>(DiffCallback) {


        class MoviesPhotoViewHolder(private var binding:
                                      GridViewItemBinding
        ):
            RecyclerView.ViewHolder(binding.root) {
            fun bind(MoviesPhoto: MoviesPhoto) {
                binding.result = MoviesPhoto
                binding.executePendingBindings()
            }
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
        ): PhotoGridAdapter.MoviesPhotoViewHolder {
            return MoviesPhotoViewHolder(GridViewItemBinding.inflate(
                LayoutInflater.from(parent.context)))    }



        override fun onBindViewHolder(holder: PhotoGridAdapter.MoviesPhotoViewHolder, position: Int) {
            val marsPhoto = getItem(position)
            holder.bind(marsPhoto)
        }
    }
