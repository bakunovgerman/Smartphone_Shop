package com.example.smartphone_shop.presentation.fragments.tab_layout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.smartphone_shop.R

class DetailItemTabLayoutFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_item_tab_layout, container, false)
    }

    companion object {

        @JvmStatic
        fun newInstance() = DetailItemTabLayoutFragment()
    }
}