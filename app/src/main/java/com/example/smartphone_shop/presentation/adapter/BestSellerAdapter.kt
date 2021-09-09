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


class BestSellerAdapter(
    private val onMovieItemClick: () -> Unit
) :
    RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {

    private val list: MutableList<BestSeller> = ArrayList()

    inner class BestSellerViewHolder(view: View, onMovieItemClick: () -> Unit) :
        RecyclerView.ViewHolder(view) {

        private val phoneRootLayout: CardView = itemView.findViewById(R.id.cvPhoneRootLayout)
        private val phoneImageView: ImageView = itemView.findViewById(R.id.imgPhone)
        private val priceWithSaleTextView: TextView = itemView.findViewById(R.id.tvPriceWithSale)
        private val priceWithoutSaleTextView: TextView =
            itemView.findViewById(R.id.tvPriceWithoutSale)
        private val namePhoneTextView: TextView = itemView.findViewById(R.id.tvNamePhone)
        private val btnFavorite: CardView = itemView.findViewById(R.id.btnFavorite)
        private val imgBtnFavorite: ImageView = itemView.findViewById(R.id.imgBtnFavorite)

        // прогресс бар для Glide
        private var circularProgressDrawable: CircularProgressDrawable =
            CircularProgressDrawable(App.applicationContext)

        init {
            phoneRootLayout.setOnClickListener { onMovieItemClick.invoke() }

            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
        }


        fun bind(bestSeller: BestSeller) {
            Glide
                .with(App.applicationContext)
                .load(bestSeller.picture)
                .placeholder(circularProgressDrawable)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(phoneImageView)
            if (bestSeller.isFavorites) {
                imgBtnFavorite.setImageResource(R.drawable.ic_favorite_on)
            } else {
                imgBtnFavorite.setImageResource(R.drawable.ic_favorite_off)
            }
            priceWithSaleTextView.text =
                String.format(
                    App.applicationContext.getString(R.string.phone_price),
                    bestSeller.discountPrice.toString()
                )
            priceWithoutSaleTextView.text = String.format(
                App.applicationContext.getString(R.string.phone_price),
                bestSeller.priceWithoutDiscount.toString()
            )
            namePhoneTextView.text = bestSeller.title
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun initData(list: List<BestSeller>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestSellerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return BestSellerViewHolder(
            inflater.inflate(R.layout.item_phone, parent, false),
            onMovieItemClick
        )
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}