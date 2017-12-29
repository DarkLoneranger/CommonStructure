package com.trs.bj.commonstructure.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by ZHAO on 2017/12/28.
 */
open class InfiniteViewPager: ViewPager, View.OnTouchListener {

    constructor(context: Context): super(context){
        addOnPageChangeListener(MySimpleOnPageChangeListener())
        setOnTouchListener(this)
    }

    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        addOnPageChangeListener(MySimpleOnPageChangeListener())
        setOnTouchListener(this)
    }

    private var currentPosition: Int = 0
    private var isFromUser: Boolean = false
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> isFromUser = true
            MotionEvent.ACTION_UP -> isFromUser = false
        }

        return false
    }

    class  MySimpleOnPageChangeListener :SimpleOnPageChangeListener(){
        override fun onPageSelected(position: Int) {

        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        }

        override fun onPageScrollStateChanged(state: Int) {
        }
    }




}