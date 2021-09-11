package com.example.smartphone_shop.presentation.adapter

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.compose.ui.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.smartphone_shop.App
import com.example.smartphone_shop.R
import com.example.smartphone_shop.repository.retrofit.entities.BestSeller
import android.text.style.UnderlineSpan

import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.StrikethroughSpan
import android.widget.RadioButton


class MemoryRadioBtnAdapter(
    private val onMemoryRadioBtnItemClick: (String) -> Unit
) :
    RecyclerView.Adapter<MemoryRadioBtnAdapter.MemoryRadioBtnViewHolder>() {

    private var selectedItem: Int = 0
    private val list: MutableList<String> = ArrayList()

    inner class MemoryRadioBtnViewHolder(view: View, onMemoryRadioBtnItemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(view) {

        private val memoryRadioBtn: RadioButton = itemView.findViewById(R.id.memoryRadioBtn)
        private lateinit var item: String

        init {
            memoryRadioBtn.setOnClickListener {
                onMemoryRadioBtnItemClick.invoke(item)
                selectedItem = adapterPosition
                notifyDataSetChanged()
            }
        }

        fun bind(radioBtnText: String, position: Int) {
            item = radioBtnText
            memoryRadioBtn.text = radioBtnText
            memoryRadioBtn.isChecked = selectedItem == position
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initData(list: List<String>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoryRadioBtnViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MemoryRadioBtnViewHolder(
            inflater.inflate(R.layout.item_memory_radio, parent, false),
            onMemoryRadioBtnItemClick
        )
    }

    override fun onBindViewHolder(holder: MemoryRadioBtnViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}