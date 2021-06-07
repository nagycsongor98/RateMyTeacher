package com.example.ratemyteacher.ui.teacherslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ratemyteacher.databinding.LayoutReviewListItemBinding
import com.example.ratemyteacher.ui.rateteacher.Review

/**
 * @author  Csongor Nagy
 * @since  07.06.2021
 */
class TeachersAdapter(
    val teachers: ArrayList<String>,
    val listener: () -> Unit
) :
    RecyclerView.Adapter<TeachersAdapter.TeachersViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersViewHolder {
        return TeachersViewHolder(
            LayoutReviewListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TeachersViewHolder, position: Int) {
        holder.bind(position, teachers[position], listener)
    }

    override fun getItemCount(): Int {
        return teachers.size
    }

    class TeachersViewHolder(val binding: LayoutReviewListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int, item: String, listener: () -> Unit) {
            binding.textView.text = "  ${position+1}: $item"
            binding.textView.setOnClickListener {
                listener.invoke()
            }
        }
    }
}