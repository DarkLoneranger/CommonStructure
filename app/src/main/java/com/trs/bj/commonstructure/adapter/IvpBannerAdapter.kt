package com.trs.bj.commonstructure.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.view.InfiniteViewPager
import com.trs.bj.commonstructure.view.InfiniteViewPagerAdapter

/**
 * Created by ZHAO on 2017/12/28.
 */
class IvpBannerAdapter : InfiniteViewPagerAdapter {
    private var context: Context?

    constructor(context: Context) {
        this.context = context
    }

    override fun getItemView(position: Int): View {
        val view = View.inflate(context, R.layout.iv_test_layout, null)
        val imageView = view.findViewById<ImageView>(R.id.iv_test)
        imageView.setImageResource(dataList!![position] as Int)
        return view
    }


}