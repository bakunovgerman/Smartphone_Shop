package com.example.smartphone_shop.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.smartphone_shop.R
import com.example.smartphone_shop.domain.MainViewModel
import com.example.smartphone_shop.presentation.adapter.CategoryAdapter
import com.example.smartphone_shop.presentation.adapter.items_decoration.SpacesItemDecoration
import com.example.smartphone_shop.repository.data.CategoryDataSourceImpl
import com.example.smartphone_shop.repository.data.CategoryDto

class MainFragment : Fragment() {

    private lateinit var rvCategory: RecyclerView
    private lateinit var mainViewModel: MainViewModel
    private val adapterCategory = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // init ViewModel
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        initSubscribe()

        // загружаем данные
        mainViewModel.getCategory()
    }

    private fun initView(view: View) {
        rvCategory = view.findViewById(R.id.rvCategory)
        rvCategory.adapter = adapterCategory
    }

    private fun initCategoryData(listCategory: List<CategoryDto>) {
        adapterCategory.initData(listCategory)
        rvCategory.addItemDecoration(
            SpacesItemDecoration(
                spaceRight = 25,
                spaceLeft = 15,
                size = listCategory.size
            )
        )
    }

    private fun initSubscribe() {
        mainViewModel.categoryList.observe(viewLifecycleOwner, Observer(::initCategoryData))
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}