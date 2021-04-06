package com.example.nytimesdemo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesdemo.databinding.BestSellerRowHeaderLayoutBinding
import com.example.nytimesdemo.databinding.BestSellerRowLayoutBinding
import com.example.nytimesdemo.databinding.ListBynameRowLayoutBinding
import com.example.nytimesdemo.models.BookDetailsItem
import com.example.nytimesdemo.models.ResultsItem
import com.example.nytimesdemo.models.UpdatedBy



class ListByNameAdapter(private val items: List<BookDetailsItem?>?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ListByNameViewHolder(
            ListBynameRowLayoutBinding.inflate(
                layoutInflater,
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ListByNameViewHolder)?.bind(items?.get(position) as? BookDetailsItem)
    }



    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class ListByNameViewHolder(val binding: ListBynameRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookDetailsItem?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }




}