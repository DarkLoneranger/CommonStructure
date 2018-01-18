package com.trs.bj.commonstructure.adapter

import android.content.Context
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ImageView
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.view.InfiniteViewPagerAdapter

/**
 * Created by ZHAO on 2017/12/28.
 */
class IvpBannerAdapter : InfiniteViewPagerAdapter {
    private var context: Context?

    constructor(context: Context, viewPager: ViewPager):super(viewPager) {
        this.context = context
        this.setScrollDuration(500)
        this.shownDuration=3000
    }

    override fun getItemView(position: Int): View {
        val view = View.inflate(context, R.layout.iv_test_layout, null)
        val imageView = view.findViewById<ImageView>(R.id.iv_test)
        imageView.setImageResource(dataList!![position] as Int)
        imageView.scaleType=ImageView.ScaleType.CENTER_CROP
        return view
    }


}