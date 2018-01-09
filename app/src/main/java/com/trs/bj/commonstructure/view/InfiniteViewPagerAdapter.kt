package com.trs.bj.commonstructure.view

import android.annotation.SuppressLint
import android.content.Context

import android.os.Handler
import android.os.Message
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.view.animation.LinearOutSlowInInterpolator
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import android.widget.Scroller

/**
 * Created by ZHAO on 2017/12/28.
 */
abstract class InfiniteViewPagerAdapter : PagerAdapter, ViewPager.OnPageChangeListener {
    open var dataList: ArrayList<Any>? = ArrayList<Any>()
    var mViewPager: ViewPager? = null

    constructor(viewPager: ViewPager) : super() {
        this.mViewPager = viewPager
        mViewPager!!.addOnPageChangeListener(this)

    }

    val handler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {

        override fun handleMessage(msg: Message) {         // handle message
            mViewPager!!.setCurrentItem((mViewPager!!.currentItem) + 1, true)
            // handler.sendEmptyMessageDelayed(0, shownDuration)
        }
    }

    override fun onPageSelected(position: Int) {
        Log.e("test", "onPageSelected" + position)
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.e("test", "onPageScrolled" + position + "--" + positionOffset + "--" + positionOffsetPixels)
    }

    open  var shownDuration : Long = 3000

    override fun onPageScrollStateChanged(state: Int) {
        Log.e("test", "onPageScrollStateChanged" + state)
        when (state) {
            ViewPager.SCROLL_STATE_IDLE -> {
                handler.sendEmptyMessageDelayed(0, shownDuration )
            }
            ViewPager.SCROLL_STATE_DRAGGING -> {
                handler.removeCallbacksAndMessages(null)
            }
            ViewPager.SCROLL_STATE_SETTLING -> {
                return
            }

        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return Int.MAX_VALUE
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container!!.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        if (dataList != null && dataList!!.size > 0) {
            var realPosition = position % dataList!!.size
            var itemView = getItemView(realPosition)
            container!!.addView(itemView)
            return itemView
        }
        return super.instantiateItem(container, position);


    }

    fun setData(dataList: ArrayList<Any>) {
        this.dataList = dataList
        notifyDataSetChanged()
        handler.sendEmptyMessage(0)
    }

    fun startScroll() {
        handler.sendEmptyMessage(0)
    }


    abstract fun getItemView(position: Int): View

    fun setScrollDuration(scrollDuration: Int){  //Kotlin反射设置字段
        try {
            val c = ViewPager::class.java
            val declaredField = c.getDeclaredField("mScroller")
            var fixedSpeedScroller =FixedSpeedScroller(mViewPager!!.context,  LinearOutSlowInInterpolator ());
            fixedSpeedScroller.mDuration= scrollDuration
            declaredField.setAccessible(true);
            declaredField.set(mViewPager, fixedSpeedScroller);
        } catch (e:Exception ) {
            e.printStackTrace();
        }
    }


    class FixedSpeedScroller : Scroller
    {

        var mDuration = 1500;
        constructor(context: Context): super(context) {

        }

        constructor (context: Context,  interpolator: Interpolator): super(context, interpolator) {

    }

        constructor (context: Context,  interpolator: Interpolator, flywheel:Boolean ): super(context, interpolator, flywheel) {

    }

        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
            startScroll(startX, startY, dx, dy, mDuration);
        }



    }


}