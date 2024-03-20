package com.example.marsrealestate.listing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.marsrealestate.databinding.GridItemBinding
import com.example.marsrealestate.network.MarsProperty

class ListingAdapter(private val itemClickListener: ItemClickListener) :
    ListAdapter<MarsProperty, ListingAdapter.ListingViewHolder>(DiffCallBack) {


    class ListingViewHolder(private val binding: GridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(marsProperty: MarsProperty) {
            binding.property = marsProperty
            binding.executePendingBindings()
        }
    }


    companion object DiffCallBack : DiffUtil.ItemCallback<MarsProperty>() {
        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListingViewHolder {
        return ListingViewHolder(
            GridItemBinding.inflate(LayoutInflater.from(parent.context))
        )

    }

    override fun onBindViewHolder(
        holder: ListingViewHolder,
        position: Int
    ) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(item)
        }
        holder.bind(item)
    }

    class ItemClickListener(val clickListener: (marsProperty: MarsProperty) -> Unit) {
        fun onClick(marsProperty: MarsProperty) = clickListener(marsProperty)
    }
}