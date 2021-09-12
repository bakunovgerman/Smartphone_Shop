package com.example.smartphone_shop.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartphone_shop.R
import com.example.smartphone_shop.repository.data.CategoryDto

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val list: MutableList<CategoryDto> = ArrayList()

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val categoryButton: ImageView = itemView.findViewById(R.id.iconCategory)
        private val nameCategoryTextView: TextView = itemView.findViewById(R.id.tvNameCategory)

        fun bind(categoryItem: CategoryDto) {
            categoryButton.setImageResource(categoryItem.icon)
            nameCategoryTextView.text = categoryItem.name
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initData(list: List<CategoryDto>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return CategoryViewHolder(inflater.inflate(R.layout.item_category, parent, false))
        }

        override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
            holder.bind(list[position])
        }

        override fun getItemCount(): Int = list.size

}