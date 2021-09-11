package com.example.smartphone_shop.presentation.adapter

import android.annotation.SuppressLint
import android.os.Build
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
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.Color
import at.markushi.ui.CircleButton


class ColorAdapter(
    private val onMemoryRadioBtnItemClick: (String) -> Unit
) :
    RecyclerView.Adapter<ColorAdapter.ColorViewHolder>() {

    private var selectedItem: Int = 0
    private val list: MutableList<String> = ArrayList()

    inner class ColorViewHolder(view: View, onMemoryRadioBtnItemClick: (String) -> Unit) :
        RecyclerView.ViewHolder(view) {

        private val colorCircleButton: CircleButton = itemView.findViewById(R.id.btnColor)
        private val iconSelect: ImageView = itemView.findViewById(R.id.iconSelectedImageView)
        private val viewDark: ImageView = itemView.findViewById(R.id.viewBlackSelected)

        init {
            colorCircleButton.setOnClickListener {
                selectedItem = adapterPosition
                notifyDataSetChanged()
            }
        }

        fun bind(color: String, position: Int) {
            colorCircleButton.setColor(android.graphics.Color.parseColor(color))
            if (selectedItem == position){
                viewDark.visibility = View.VISIBLE
                iconSelect.visibility = View.VISIBLE
            }
            else {
                viewDark.visibility = View.INVISIBLE
                iconSelect.visibility = View.INVISIBLE
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initData(list: List<String>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ColorViewHolder(
            inflater.inflate(R.layout.item_color, parent, false),
            onMemoryRadioBtnItemClick
        )
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}