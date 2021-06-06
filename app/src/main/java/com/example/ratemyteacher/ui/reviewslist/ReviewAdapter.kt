package com.example.ratemyteacher.ui.reviewslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ratemyteacher.databinding.LayoutReviewListItemBinding
import com.example.ratemyteacher.ui.rateteacher.Review

/**
 * @author  Csongor Nagy
 * @since  06.06.2021
 */
class ReviewAdapter(val reviews: ArrayList<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            LayoutReviewListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(position, reviews[position])
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    class ReviewViewHolder(val binding: LayoutReviewListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, item: Review) {
            binding.textView.text = "  ${position+1}: ${item.reviewString}"
        }

    }

}