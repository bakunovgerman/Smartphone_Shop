package com.example.smartphone_shop.features.mainScreen.presentation.adapter.items_decoration

import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(
    private val spaceRight: Int = 0,
    private val spaceBottom: Int = 0,
    private val spaceLeft: Int = 0,
    private val size: Int
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.left = dpToPx(spaceLeft)
                outRect.right = dpToPx(spaceRight)
            }
            size - 1 -> outRect.right = dpToPx(spaceLeft)
            else -> {
                outRect.bottom = spaceBottom
                outRect.right = dpToPx(spaceRight)
            }
        }
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }
}