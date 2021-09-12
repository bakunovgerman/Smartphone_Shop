package com.example.smartphone_shop.presentation.adapter

import android.annotation.SuppressLint
import android.app.usage.NetworkStats
import android.media.Image
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
import android.widget.Button
import android.widget.ImageButton
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.smartphone_shop.repository.retrofit.entities.Basket
import org.w3c.dom.Text


class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private val list: MutableList<Basket> = ArrayList()

    inner class CartViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        private val itemPhotoImageView: ImageView = itemView.findViewById(R.id.imgProduct)
        private val nameProductTextView: TextView = itemView.findViewById(R.id.tvNameProduct)
        private val priceProductTextView: TextView = itemView.findViewById(R.id.tvPriceProduct)
        private val minusCountImageButton: ImageButton = itemView.findViewById(R.id.btnMinusCount)
        private val plusCountImageButton: ImageButton = itemView.findViewById(R.id.btnPlusCount)
        private val countTextView: TextView = itemView.findViewById(R.id.tvCountProduct)
        private val removeItemButton: Button = itemView.findViewById(R.id.btnRemoveCount)

        init {
            minusCountImageButton.setOnClickListener {
                countTextView.text = countTextView.text.toString().toInt().minus(1).toString()
            }
            plusCountImageButton.setOnClickListener {
                countTextView.text = countTextView.text.toString().toInt().plus(1).toString()
            }
            removeItemButton.setOnClickListener {
                notifyItemRemoved(adapterPosition)
            }
        }

        fun bind(basket: Basket) {
            Glide
                .with(itemView.context)
                .load(basket.images)
                .transition(DrawableTransitionOptions.withCrossFade())
                .transform(RoundedCorners(10))
                .centerCrop()
                .into(itemPhotoImageView)
            countTextView.text = "1"
            nameProductTextView.text = basket.title
            priceProductTextView.text =
                String.format(itemView.context.getString(R.string.phone_price), basket.price)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initData(list: List<Basket>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CartViewHolder(
            inflater.inflate(R.layout.item_cart, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}