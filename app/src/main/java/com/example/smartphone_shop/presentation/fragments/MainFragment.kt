package com.example.smartphone_shop.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewpager2.widget.ViewPager2
import com.example.smartphone_shop.R
import com.example.smartphone_shop.domain.MainViewModel
import com.example.smartphone_shop.presentation.adapter.BestSellerAdapter
import com.example.smartphone_shop.presentation.adapter.CategoryAdapter
import com.example.smartphone_shop.presentation.adapter.HomeStoreAdapter
import com.example.smartphone_shop.presentation.adapter.items_decoration.GridSpacingItemDecoration
import com.example.smartphone_shop.presentation.adapter.items_decoration.SpacesItemDecoration
import com.example.smartphone_shop.repository.data.CategoryDto

class MainFragment : Fragment() {

    private lateinit var rvCategory: RecyclerView
    private lateinit var rvPhones: RecyclerView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var homeStoreViewPager2: ViewPager2
    private val adapterCategory = CategoryAdapter()
    private lateinit var adapterHomeStore: HomeStoreAdapter
    private lateinit var adapterBestSeller: BestSellerAdapter

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
        initRv()
        initSubscribe()
        loadData()

    }

    private fun initRv() {
        rvCategory.adapter = adapterCategory
        adapterHomeStore = HomeStoreAdapter()
        adapterBestSeller = BestSellerAdapter {

        }
        homeStoreViewPager2.adapter = adapterHomeStore
        rvPhones.apply {
            adapter = adapterBestSeller
            layoutManager = GridLayoutManager(context, 2)
            addItemDecoration(GridSpacingItemDecoration(12))
        }
    }

    private fun loadData() {
        // загружаем данные
        mainViewModel.getCategory()
        mainViewModel.getMainInfo(getString(R.string.api_key))
    }

    private fun initView(view: View) {
        rvCategory = view.findViewById(R.id.rvCategory)
        rvPhones = view.findViewById(R.id.rvPhones)
        homeStoreViewPager2 = view.findViewById(R.id.vp2HomeStore)
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
        mainViewModel.homeStore.observe(viewLifecycleOwner, Observer(adapterHomeStore::initData))
        mainViewModel.bestSeller.observe(viewLifecycleOwner, Observer(adapterBestSeller::initData))
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}