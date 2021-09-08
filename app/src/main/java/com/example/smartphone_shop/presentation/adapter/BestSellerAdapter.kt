package com.example.smartphone_shop.presentation.adapter

import android.annotation.SuppressLint
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

class BestSellerAdapter(private val circularProgressDrawable: CircularProgressDrawable) :
    RecyclerView.Adapter<BestSellerAdapter.BestSellerViewHolder>() {

    private val list: MutableList<BestSeller> = ArrayList()

    inner class BestSellerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val phoneImageView: ImageView = itemView.findViewById(R.id.imgPhone)
        private val priceWithSaleTextView: TextView = itemView.findViewById(R.id.tvPriceWithSale)
        private val priceWithoutSaleTextView: TextView =
            itemView.findViewById(R.id.tvPriceWithoutSale)
        private val namePhoneTextView: TextView = itemView.findViewById(R.id.tvNamePhone)
        private val btnFavorite: CardView = itemView.findViewById(R.id.btnFavorite)
        private val imgBtnFavorite: ImageView = itemView.findViewById(R.id.imgBtnFavorite)


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
            priceWithoutSaleTextView.text =
                String.format(
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
        return BestSellerViewHolder(inflater.inflate(R.layout.item_phone, parent, false))
    }

    override fun onBindViewHolder(holder: BestSellerViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}