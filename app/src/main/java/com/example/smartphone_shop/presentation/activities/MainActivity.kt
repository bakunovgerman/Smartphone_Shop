package com.example.smartphone_shop.presentation.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.smartphone_shop.R
import com.example.smartphone_shop.presentation.helpers.MainFragmentClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), MainFragmentClickListener {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        navController = findNavController(R.id.nav_host)
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.detailFragment)
            navController.popBackStack()
        else
            super.onBackPressed()
    }

    override fun onOpenDetailPhoneFragmentClick() {
        navController.navigate(R.id.detailFragment)
    }

    override fun onOpenMainFragmentClick() {
        navController.navigate(R.id.mainFragment)
    }

    override fun onOpenCartFragmentClick() {
        navController.navigate(R.id.cartFragment)
    }

}