package com.example.smartphone_shop.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartphone_shop.R
import com.example.smartphone_shop.domain.CartViewModel
import com.example.smartphone_shop.presentation.adapter.CartAdapter
import com.example.smartphone_shop.presentation.adapter.items_decoration.SpacesItemDecoration
import com.example.smartphone_shop.presentation.helpers.FragmentClickListener
import com.example.smartphone_shop.presentation.helpers.ViewStateScreen
import com.example.smartphone_shop.repository.retrofit.entities.Basket
import com.example.smartphone_shop.repository.retrofit.entities.CartResponseItem
import com.google.android.material.snackbar.Snackbar

class CartFragment : Fragment() {

    private lateinit var mainFragmentRootLayout: CoordinatorLayout
    private lateinit var progressBarLayout: FrameLayout
    private lateinit var cartBottomSheetBehavior: CardView
    private lateinit var cartViewModel: CartViewModel
    private lateinit var backButton: Button
    private lateinit var addAddressButton: Button
    private lateinit var rvItemsCart: RecyclerView
    private lateinit var totalPriceTextView: TextView
    private lateinit var deliveryTextView: TextView
    private lateinit var cartAdapter: CartAdapter
    private var fragmentClickListener: FragmentClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        showProgressBar()
        initListener()
        initRv()
        initSubscribe()

        cartViewModel.getDetailInfo(getString(R.string.api_key))
    }

    private fun initListener() {
        backButton.setOnClickListener {
            fragmentClickListener?.onBackFragmentClick()
        }
    }

    private fun initRv() {
        cartAdapter = CartAdapter {
            changeTotalPrice(it)
        }
        rvItemsCart.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun changeTotalPrice(price: Int) {
        totalPriceTextView.text =
            String.format(getString(R.string.total_price_cart), price.toDouble())
    }

    private fun initSubscribe() {
        cartViewModel.cartInfo.observe(viewLifecycleOwner, Observer(::setCartData))
        cartViewModel.viewState.observe(viewLifecycleOwner, Observer(::setViewState))
    }

    private fun setCartData(cartResponseItem: CartResponseItem) = with(cartResponseItem) {
        initDataRv(this.basket)
        totalPriceTextView.text = String.format(
            getString(R.string.total_price_cart),
            this.total.toString()
        )
        deliveryTextView.text = this.delivery
    }

    private fun initDataRv(basketList: List<Basket>) {
        cartAdapter.initData(basketList)
        Log.d("basketList", basketList.size.toString())
        if (rvItemsCart.itemDecorationCount == 0)
            rvItemsCart.addItemDecoration(
                SpacesItemDecoration(
                    spaceBottom = 45,
                    size = basketList.size
                )
            )
    }

    private fun initView(view: View) {
        backButton = view.findViewById(R.id.btnBack)
        addAddressButton = view.findViewById(R.id.btnAddAddress)
        rvItemsCart = view.findViewById(R.id.rvItemsCart)
        totalPriceTextView = view.findViewById(R.id.tvTotalPrice)
        deliveryTextView = view.findViewById(R.id.tvTypeDelivery)
        mainFragmentRootLayout = view.findViewById(R.id.fragmentCartRootView)
        cartBottomSheetBehavior = view.findViewById(R.id.bottom_sheet_cart)
        progressBarLayout = view.findViewById(R.id.progressBarLayout)
    }

    private fun setViewState(viewStateScreen: ViewStateScreen) = with(viewStateScreen) {
        if (isDownloaded)
            hideProgressBar()
        else if (e != null)
            showException(e)
    }

    private fun showProgressBar() {
        progressBarLayout.visibility = View.VISIBLE
        cartBottomSheetBehavior.visibility = View.INVISIBLE
    }

    private fun hideProgressBar() {
        progressBarLayout.visibility = View.INVISIBLE
        cartBottomSheetBehavior.visibility = View.VISIBLE
    }

    private fun showException(e: Throwable) {
        Snackbar.make(mainFragmentRootLayout, getString(R.string.exception), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentClickListener)
            fragmentClickListener = context
    }

    override fun onDetach() {
        super.onDetach()
        fragmentClickListener = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = CartFragment()
    }
}