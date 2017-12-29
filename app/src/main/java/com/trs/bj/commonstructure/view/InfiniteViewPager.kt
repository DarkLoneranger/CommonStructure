package com.trs.bj.commonstructure.view

import android.content.Context
import android.os.Handler
import android.os.Message
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Switch

/**
 * Created by ZHAO on 2017/12/28.
 */
open class InfiniteViewPager : ViewPager, View.OnTouchListener {

    constructor(context: Context) : super(context) {
        addOnPageChangeListener(MySimpleOnPageChangeListener())
        setOnTouchListener(this)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        addOnPageChangeListener(MySimpleOnPageChangeListener())
        setOnTouchListener(this)
    }

    private var currentPosition: Int = 0
    var isFromUser: Boolean = false
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                isFromUser = true
                handler.removeCallbacksAndMessages(null)
            }
            MotionEvent.ACTION_UP -> {
                isFromUser = false
                handler.removeCallbacksAndMessages(null)
                val message = Message.obtain()
                message.arg1 = this.currentPosition
                handler.sendMessage(message)
            }

            MotionEvent.ACTION_MOVE ->{
                isFromUser = false
                handler.removeCallbacksAndMessages(null)
            }

        }

        return false
    }

    inner class MySimpleOnPageChangeListener : ViewPager.SimpleOnPageChangeListener() {
        var currentPosition: Int? = 0

        val handler: Handler = object : Handler() {

            override fun handleMessage(msg: Message) {         // handle message
                var position = msg.arg1
                this@InfiniteViewPager.setCurrentItem(position + 1, true)
            }
        }

        override fun onPageSelected(position: Int) {
            /*    Log.e("test", "onPageSelected" + position)
                if (!isFromUser) {
                    val message = Message.obtain()
                    message.arg1 = position
                    handler.sendMessageDelayed(message, 3000)
                }*/


        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            Log.e("test", "onPageScrolled" + position + "--" + positionOffset + "--" + positionOffsetPixels)
            //用inner 修饰之后才能访问外部类对象
            var zero = 0
            if (!isFromUser && (positionOffset == zero.toFloat()) && (positionOffsetPixels == zero.toInt())) {
                // this@InfiniteViewPager.setCurrentItem(position + 1,true)
                val message = Message.obtain()
                message.arg1 = position
                handler.sendMessageDelayed(message, 3000)
            }

        }

        override fun onPageScrollStateChanged(state: Int) {
            //Log.e("test", "onPageScrollStateChanged" + state)

        }
    }


}