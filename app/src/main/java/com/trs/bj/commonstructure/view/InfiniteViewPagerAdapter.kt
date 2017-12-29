package com.trs.bj.commonstructure.view

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup

/**
 * Created by ZHAO on 2017/12/28.
 */
abstract class InfiniteViewPagerAdapter : PagerAdapter() {
    open var dataList: ArrayList<Any>? = ArrayList<Any>()

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
        container!!.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup?, position: Int): Any? {
        if (dataList != null && dataList!!.size > 0) {
            var realPosition = position % dataList!!.size
            var itemView = getItemView(realPosition)
            container!!.addView(itemView)
            return itemView
        }
        return null


    }

    fun setData(dataList: ArrayList<Any>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    abstract fun getItemView(position: Int): View


}