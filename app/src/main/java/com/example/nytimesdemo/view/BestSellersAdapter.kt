package com.example.nytimesdemo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nytimesdemo.databinding.BestSellerRowHeaderLayoutBinding
import com.example.nytimesdemo.databinding.BestSellerRowLayoutBinding
import com.example.nytimesdemo.models.ResultsItem
import com.example.nytimesdemo.models.UpdatedBy

const val VIEW_TYPE_HEADER = 0;
const val VIEW_TYPE_BOOK = 1;

class BestSellersAdapter(private val items: List<Any?>?,private val listener: ((item : Any?)->Unit)?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        when (viewType) {
            VIEW_TYPE_BOOK -> {
                return BestSellerViewHolder(
                    BestSellerRowLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            VIEW_TYPE_HEADER -> {
                return BestSellerHeaderViewHolder(
                    BestSellerRowHeaderLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }
            else -> {
                return BestSellerViewHolder(
                    BestSellerRowLayoutBinding.inflate(
                        layoutInflater,
                        parent,
                        false
                    )
                )
            }

        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            VIEW_TYPE_BOOK -> {
                (holder as? BestSellerViewHolder)?.bind(items?.get(position) as? ResultsItem)
            }
            VIEW_TYPE_HEADER -> {
                (holder as? BestSellerHeaderViewHolder)?.bind(items?.get(position) as? UpdatedBy)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (items?.get(position)) {
            is UpdatedBy -> return VIEW_TYPE_HEADER
            is ResultsItem -> return VIEW_TYPE_BOOK
            else -> return VIEW_TYPE_HEADER
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    inner class BestSellerViewHolder(val binding: BestSellerRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: ResultsItem?) {
            binding.root.setOnClickListener {
                this@BestSellersAdapter.listener?.invoke(this@BestSellersAdapter.items?.get(adapterPosition))
            }
            binding?.model = model
            binding?.executePendingBindings()
        }
    }

    class BestSellerHeaderViewHolder(val binding: BestSellerRowHeaderLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: UpdatedBy?) {
            binding?.model = model
            binding?.executePendingBindings()
        }
    }


}