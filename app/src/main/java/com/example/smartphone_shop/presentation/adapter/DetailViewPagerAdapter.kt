package com.example.smartphone_shop.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.smartphone_shop.presentation.fragments.DetailItemTabLayoutFragment
import com.example.smartphone_shop.presentation.fragments.FeaturesItemTabLayoutFragment
import com.example.smartphone_shop.presentation.fragments.ShopItemTabLayoutFragment
import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponseItem

class DetailViewPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val detailInfoResponseItem: DetailInfoResponseItem
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ShopItemTabLayoutFragment.newInstance(detailInfoResponseItem)
            1 -> DetailItemTabLayoutFragment.newInstance()
            else -> FeaturesItemTabLayoutFragment.newInstance()
        }
    }
}