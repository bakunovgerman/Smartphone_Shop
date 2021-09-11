package com.example.smartphone_shop.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.smartphone_shop.R
import com.example.smartphone_shop.domain.DetailViewModel
import com.example.smartphone_shop.presentation.adapter.ColorAdapter
import com.example.smartphone_shop.presentation.adapter.DetailViewPagerAdapter
import com.example.smartphone_shop.presentation.adapter.MemoryRadioBtnAdapter
import com.example.smartphone_shop.presentation.adapter.PhoneImagesAdapter
import com.example.smartphone_shop.repository.retrofit.entities.DetailInfoResponseItem
import com.google.android.material.tabs.TabLayout

class DetailFragment : Fragment() {

    private lateinit var backButton: Button
    private lateinit var cartButton: Button
    private lateinit var phonePhotosViewPager2: ViewPager2
    private lateinit var namePhoneTextView: TextView
    private lateinit var favoriteButton: Button
    private lateinit var phoneRatingBar: RatingBar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var rvColor: RecyclerView
    private lateinit var rvMemory: RecyclerView
    private lateinit var addCartButton: Button
    private lateinit var detailViewModel: DetailViewModel
    private val phoneImagesAdapter = PhoneImagesAdapter()
    private lateinit var detailViewPagerAdapter: DetailViewPagerAdapter
    private lateinit var memoryRadioBtnAdapter: MemoryRadioBtnAdapter
    private lateinit var colorAdapter: ColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        initListener()
        initRvAndVp()
        initTabLayout()
        initSubscribe()

        detailViewModel.getDetailInfo(getString(R.string.api_key))
    }

    private fun initTabLayout() {
        tabLayout.apply {
            addTab(this.newTab().setText("Shop"))
            addTab(this.newTab().setText("Details"))
            addTab(this.newTab().setText("Features"))
        }
    }

    private fun initRvAndVp() {
        phonePhotosViewPager2.adapter = phoneImagesAdapter
        memoryRadioBtnAdapter = MemoryRadioBtnAdapter {

        }
        rvMemory.adapter = memoryRadioBtnAdapter
        colorAdapter = ColorAdapter {

        }
        rvColor.adapter = colorAdapter
    }

    private fun initSubscribe() {
        detailViewModel.detailInfo.observe(viewLifecycleOwner, Observer(::setDetailData))
    }

    private fun setDetailData(detailInfoResponseItem: DetailInfoResponseItem) {
        phoneImagesAdapter.initData(detailInfoResponseItem.images)
        namePhoneTextView.text = detailInfoResponseItem.title
        phoneRatingBar.rating = detailInfoResponseItem.rating.toFloat()
        viewPager.adapter = DetailViewPagerAdapter(parentFragmentManager, lifecycle, detailInfoResponseItem)
        memoryRadioBtnAdapter.initData(detailInfoResponseItem.capacity)
        addCartButton.text = String.format(getString(R.string.phone_price_cart), detailInfoResponseItem.price)
        colorAdapter.initData(detailInfoResponseItem.color)
    }

    private fun initView(view: View) {
        backButton = view.findViewById(R.id.btnBack)
        cartButton = view.findViewById(R.id.btnCart)
        phonePhotosViewPager2 = view.findViewById(R.id.vp2PhonePhotos)
        namePhoneTextView = view.findViewById(R.id.tvNamePhone)
        favoriteButton = view.findViewById(R.id.btnFavorite)
        phoneRatingBar = view.findViewById(R.id.rbPhone)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.vp2DetailPhone)
        rvColor = view.findViewById(R.id.rvSelectColor)
        rvMemory = view.findViewById(R.id.rvSelectMemory)
        addCartButton = view.findViewById(R.id.btnAddCart)
    }

    private fun initListener() {
        // слушатель на клики по табу
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewPager.currentItem = it.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
        // слушатель на свайп по viewPager и сет коррктного таба исходя из видимого фрагмента
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.apply {
                    selectTab(this.getTabAt(position))
                }
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailFragment()
    }
}