package com.trs.bj.commonstructure.adapter

import android.content.Context
import android.content.Intent
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.trs.bj.commonstructure.activity.MainActivity
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.activity.SplashActivity

/**
 * Created by ZHAO on 2017/12/25.
 */
class VpWelcomeAdapter : PagerAdapter{
    var welcome_image_array: IntArray = intArrayOf(R.mipmap.guide_1, R.mipmap.guide_2)
    var context: Context? = null

    constructor(context: Context) {
        this.context = context
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object`
    }

    override fun getCount(): Int {
        return welcome_image_array.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
/*        val view = View.inflate(context, R.layout.vp_item_welcome, null)
        val iv_welcome_item = view.findViewById<ImageView>(R.id.iv_welcome_item)
        iv_welcome_item.setImageResource(welcome_image_array[position])*/
        val view = ImageView(context)
        container!!.addView(view)  //必须放在获取LayoutParams之前，不然会报空指针
        val layoutParams = view.layoutParams as ViewPager.LayoutParams
        layoutParams.height= ViewPager.LayoutParams.MATCH_PARENT
        layoutParams.width=ViewPager.LayoutParams.MATCH_PARENT
        view.layoutParams=layoutParams
        view.setImageResource(welcome_image_array[position])

        if (position==(welcome_image_array.size-1))
            view.setOnClickListener{v->  //lambda表达式
                if (context is SplashActivity) {
                    val splashActivity = context as SplashActivity
                    val intent = Intent()
                    //获取intent对象
                    intent.setClass(splashActivity, MainActivity::class.java)
                    // 获取class是使用::反射
                    splashActivity.startActivity(intent)
                    splashActivity.finish()
                } }

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View)
            container!!.removeView(`object`)
    }

}