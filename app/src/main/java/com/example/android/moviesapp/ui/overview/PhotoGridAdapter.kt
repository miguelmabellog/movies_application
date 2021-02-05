package com.example.android.moviesapp.ui.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.moviesapp.databinding.GridViewItemBinding
import com.example.android.moviesapp.domain.ModelMovie


class PhotoGridAdapter( private val onClickListener: OnClickListener ) :
        ListAdapter<ModelMovie,
                PhotoGridAdapter.ViewHolder>(DiffCallback) {

    class ViewHolder(private var binding: GridViewItemBinding):
            RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: ModelMovie) {
            binding.property = movie
            binding.executePendingBindings()
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<ModelMovie>() {
        override fun areItemsTheSame(oldItem: ModelMovie, newItem: ModelMovie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ModelMovie, newItem: ModelMovie): Boolean {
            return oldItem.id == newItem.id
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        return ViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movie)
        }
        holder.bind(movie)
    }


    class OnClickListener(val clickListener: (movie:ModelMovie) -> Unit) {
        fun onClick(movie:ModelMovie) = clickListener(movie)
    }
}

