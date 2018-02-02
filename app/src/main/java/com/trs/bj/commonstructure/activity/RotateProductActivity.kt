package com.trs.bj.commonstructure.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.MotionEvent
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.utils.ScreenUtil
import kotlinx.android.synthetic.main.activity_rotate_product.*

class RotateProductActivity : AppCompatActivity() {

    var initX=0.toFloat()
            var images: ArrayList<Int>? = arrayListOf(
            R.mipmap.object3_360_0, R.mipmap.object3_360_1, R.mipmap.object3_360_2, R.mipmap.object3_360_3, R.mipmap.object3_360_4,
            R.mipmap.object3_360_5, R.mipmap.object3_360_6, R.mipmap.object3_360_7, R.mipmap.object3_360_8, R.mipmap.object3_360_9,
            R.mipmap.object3_360_10, R.mipmap.object3_360_11, R.mipmap.object3_360_12, R.mipmap.object3_360_13, R.mipmap.object3_360_14,
            R.mipmap.object3_360_15, R.mipmap.object3_360_16, R.mipmap.object3_360_17, R.mipmap.object3_360_18, R.mipmap.object3_360_19,
            R.mipmap.object3_360_20, R.mipmap.object3_360_21, R.mipmap.object3_360_22, R.mipmap.object3_360_23, R.mipmap.object3_360_24,
            R.mipmap.object3_360_25, R.mipmap.object3_360_26, R.mipmap.object3_360_27, R.mipmap.object3_360_28, R.mipmap.object3_360_29,
            R.mipmap.object3_360_30, R.mipmap.object3_360_31, R.mipmap.object3_360_32, R.mipmap.object3_360_33, R.mipmap.object3_360_34,
            R.mipmap.object3_360_35, R.mipmap.object3_360_36, R.mipmap.object3_360_37, R.mipmap.object3_360_38, R.mipmap.object3_360_39)
    var mTouchSlop =0
    var currentImageIndex:Int?=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rotate_product)
        //屏幕从头到尾横向滑动的距离能让产品转几圈
        mTouchSlop = ScreenUtil.getScreenWidth(this) / (images!!.size*8)
        iv_rotate.setOnTouchListener {
            v, event ->
            when(event.action){
                MotionEvent.ACTION_DOWN ->{

                    initX= event.rawX
                    v.parent.requestDisallowInterceptTouchEvent(true)
                    return@setOnTouchListener true

                }

                MotionEvent.ACTION_MOVE ->{
                    var currentX = event.rawX
                    val deltaX= currentX - this!!.initX!!
                    initX = currentX
                    //向右
                    if (deltaX>0 && deltaX> mTouchSlop){
                        val rotateCount = deltaX / mTouchSlop
                       for (j in 1..rotateCount.toInt()){
                           val i1 = currentImageIndex!!.plus(1)
                           currentImageIndex = if (i1 == images!!.size) 0 else i1
                           mHandler?.let { it.sendEmptyMessageDelayed(currentImageIndex!!.toInt(), 2) }
                       }
                        return@setOnTouchListener true
                    }

                    //向左
                    if (deltaX < 0 && -deltaX > mTouchSlop) {
                        val rotateCount = -deltaX / mTouchSlop
                        for (j in 1..rotateCount.toInt()) {
                            val i1 = currentImageIndex!!.minus(1)
                            currentImageIndex = if (i1 == -1) images!!.size - 1 else i1
                            mHandler?.let { it.sendEmptyMessageDelayed(currentImageIndex!!.toInt(), 2) }

                        }
                        return@setOnTouchListener true
                    }

                    return@setOnTouchListener false
                }
                MotionEvent.ACTION_UP ->{
                    mHandler?.let { it.removeCallbacksAndMessages(null)}
                    return@setOnTouchListener false
                }
                else->{
                    return@setOnTouchListener false
                }


            }

          }

    }

    private var mHandler: Handler? = @SuppressLint("HandlerLeak")
    object : Handler() {

        override fun handleMessage(msg: Message) {
            removeCallbacksAndMessages(null) //防止滑动过快时旋转卡顿
            val what = msg?.what
                iv_rotate.setImageResource(images!![what])

        }
    }


}
