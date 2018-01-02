package com.trs.bj.commonstructure.view

import android.content.Context
import android.os.Handler
import android.os.Message
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.ViewGroup

/**
 * Created by ZHAO on 2017/12/28.
 */
abstract class InfiniteViewPagerAdapter : PagerAdapter,ViewPager.OnPageChangeListener{
    open var dataList: ArrayList<Any>? = ArrayList<Any>()
    var mViewPager: ViewPager?=null
    constructor(viewPager:ViewPager):super(){
        this.mViewPager=viewPager
        mViewPager!!.addOnPageChangeListener(this)

    }

    val handler: Handler = object : Handler() {

        override fun handleMessage(msg: Message) {         // handle message
            mViewPager!!.setCurrentItem((mViewPager!!.currentItem)+1, true)
            // handler.sendEmptyMessageDelayed(0, 3000)
        }
    }

    override fun onPageSelected(position: Int) {
        Log.e("test", "onPageSelected" + position)
        /*    Log.e("test", "onPageSelected" + position)
            if (!isFromUser) {
                val message = Message.obtain()
                message.arg1 = position
                handler.sendEmptyMessageDelayed(3000)
            }*/


    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        Log.e("test", "onPageScrolled" + position + "--" + positionOffset + "--" + positionOffsetPixels)
        /*  Log.e("test", "onPageScrolled" + position + "--" + positionOffset + "--" + positionOffsetPixels)
          //用inner 修饰之后才能访问外部类对象
          var zero = 0
          if (!isFromUser && (positionOffset == zero.toFloat()) && (positionOffsetPixels == zero.toInt())) {
              // .setCurrentItem(position + 1,true)
              handler.sendEmptyMessageDelayed(0,3000)
          }*/

    }

    override fun onPageScrollStateChanged(state: Int) {
        Log.e("test", "onPageScrollStateChanged" + state)
        when (state) {
            ViewPager.SCROLL_STATE_IDLE -> {
                return
            }
            ViewPager.SCROLL_STATE_DRAGGING -> {
                handler.removeCallbacksAndMessages(null)
            }
            ViewPager.SCROLL_STATE_SETTLING -> {
                handler.sendEmptyMessageDelayed(0, 3000)
            }

        }
    }

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
        handler.sendEmptyMessage(0)
    }


    abstract fun getItemView(position: Int): View


}