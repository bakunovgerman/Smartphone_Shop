package com.example.smartphone_shop.presentation.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
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
import com.example.smartphone_shop.presentation.helpers.MainFragmentClickListener
import com.example.smartphone_shop.presentation.helpers.ViewStateScreen
import com.example.smartphone_shop.repository.data.CategoryDto
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    private lateinit var mainFragmentRootLayout: ConstraintLayout
    private lateinit var rvCategory: RecyclerView
    private lateinit var rvPhones: RecyclerView
    private lateinit var mainViewModel: MainViewModel
    private lateinit var homeStoreViewPager2: ViewPager2
    private val adapterCategory = CategoryAdapter()
    private lateinit var adapterHomeStore: HomeStoreAdapter
    private lateinit var adapterBestSeller: BestSellerAdapter
    private lateinit var progressBarLayout: FrameLayout

    private var mainFragmentClickListener: MainFragmentClickListener? = null

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
            mainFragmentClickListener?.onOpenDetailPhoneClick()
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
        mainFragmentRootLayout = view.findViewById(R.id.mainFragmentRootLayout)
        rvCategory = view.findViewById(R.id.rvCategory)
        rvPhones = view.findViewById(R.id.rvPhones)
        homeStoreViewPager2 = view.findViewById(R.id.vp2HomeStore)
        progressBarLayout = view.findViewById(R.id.progressBarLayout)
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

    private fun setViewState(viewStateScreen: ViewStateScreen) = with(viewStateScreen) {
        if (isDownloaded)
            hideProgressBar()
        else if (e != null)
            showException(e)
    }

    private fun showProgressBar() {
        progressBarLayout.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressBarLayout.visibility = View.INVISIBLE
    }

    private fun showException(e: Throwable) {
        Snackbar.make(mainFragmentRootLayout, getString(R.string.exception), Snackbar.LENGTH_LONG).show()
    }

    private fun initSubscribe() {
        mainViewModel.categoryList.observe(viewLifecycleOwner, Observer(::initCategoryData))
        mainViewModel.homeStore.observe(viewLifecycleOwner, Observer(adapterHomeStore::initData))
        mainViewModel.bestSeller.observe(viewLifecycleOwner, Observer(adapterBestSeller::initData))
        mainViewModel.viewState.observe(viewLifecycleOwner, Observer(::setViewState))
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainFragmentClickListener)
            mainFragmentClickListener = context
    }

    override fun onDetach() {
        super.onDetach()
        mainFragmentClickListener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}