package com.example.smartphone_shop.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartphone_shop.R
import com.example.smartphone_shop.domain.CartViewModel
import com.example.smartphone_shop.presentation.helpers.FragmentClickListener
import com.example.smartphone_shop.repository.retrofit.entities.CartResponseItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), FragmentClickListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController
    private lateinit var cartViewModel: CartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initListener()
        initSubscribe()

        cartViewModel.getDetailInfo(getString(R.string.api_key))
    }

    private fun initListener() {
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main -> {
                    if (navController.currentDestination?.id == R.id.detailFragment) {
                        navController.navigate(R.id.action_detail_fragment_to_main_fragment)
                    } else if (navController.currentDestination?.id == R.id.cartFragment) {
                        navController.navigate(R.id.action_cart_fragment_to_main_fragment)
                    }
                    true
                }
                R.id.cart -> {
                    if (navController.currentDestination?.id == R.id.mainFragment) {
                        navController.navigate(R.id.action_main_fragment_to_cart_fragment)
                    } else if (navController.currentDestination?.id == R.id.detailFragment) {
                        navController.navigate(R.id.action_detail_fragment_to_cart_fragment)
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun initView() {
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        bottomNavigationView = findViewById(R.id.bottom_navigation)
        navController = findNavController(R.id.nav_host)
        bottomNavigationView.setupWithNavController(navController)
    }

    private fun initSubscribe() {
        cartViewModel.cartInfo.observe(this, Observer(::setCountItemCart))
    }


    private fun setCountItemCart(cartResponseItem: CartResponseItem) {
        bottomNavigationView.getOrCreateBadge(R.id.cart).apply {
            number = cartResponseItem.basket.size
            if (number > 0)
                isVisible = true
        }
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.detailFragment)
            navController.popBackStack()
        else
            super.onBackPressed()
    }

    override fun onOpenDetailFragmentClick() {
        navController.navigate(R.id.detailFragment)
    }

    override fun onOpenMainFragmentClick() {
        navController.navigate(R.id.mainFragment)
    }

    override fun onOpenCartFragmentClick() {
        navController.navigate(R.id.cartFragment)
    }

}