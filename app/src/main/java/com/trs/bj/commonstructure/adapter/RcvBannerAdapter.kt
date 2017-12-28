package com.trs.bj.commonstructure.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.view.RcvHeaderAdapter

/**
 * Created by ZHAO on 2017/12/27.
 */
class RcvBannerAdapter : RcvHeaderAdapter {
    var context: Context?=null
    constructor(context: Context):super(context){
        this.context=context
    }
    override fun bindHeaderLayoutResource(): HeaderViewHolder {
        val view = View.inflate(context, R.layout.iv_test_layout, null)
        return BannerHeaderHolder(view)
    }

    override fun bindItemLayoutResource(): ItemViewHolder {
        val view = View.inflate(context, R.layout.tv_test_layout, null)
        return BannerItemHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: HeaderViewHolder?, headerData: ArrayList<Any>?) {
        if (holder is BannerHeaderHolder){
            holder.iv_test!!.setImageResource(R.mipmap.guide_1)
        }
    }

    override fun onBindItemViewHolder(holder: ItemViewHolder?, position: Int, listData: ArrayList<Any>?) {
        if (holder is BannerItemHolder) {
            holder.tv_test!!.setText("lalala"+position)
        }
    }


    class BannerHeaderHolder(view: View) : RcvHeaderAdapter.HeaderViewHolder(view) {
    //    var vp_banner :ViewPager ?= null
        var iv_test :ImageView ?= null
        override fun initView(view: View) {
            super.initView(view)
            val iv_test = view.findViewById<ImageView>(R.id.iv_test)
            this.iv_test=iv_test
        }


    }

    class BannerItemHolder(view: View) : RcvHeaderAdapter.ItemViewHolder(view) {
        var tv_test: TextView? = null
        override fun initView(view: View) {
            super.initView(view)
            val tv_test = view.findViewById<TextView>(R.id.tv_test)
            this.tv_test = tv_test
        }

    }
}