package com.trs.bj.commonstructure.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.*
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.adapter.VpWelcomeAdapter
import com.trs.bj.commonstructure.utils.SharePreferenceUtil
import kotlinx.android.synthetic.main.activity_splash.*
import java.lang.ref.WeakReference


class SplashActivity : AppCompatActivity() {

/*    private var mHandler: Handler = @SuppressLint("HandlerLeak")
    object : Handler() {     //此处的object 要加，否则无法重写 handlerMessage
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    val intent = Intent()
                    //获取intent对象
                    intent.setClass(this@SplashActivity, MainActivity::class.java)  //内部类引用外部类对象
                    // 获取class是使用::反射
                    this@SplashActivity.startActivity(intent)
                    this@SplashActivity.finish()
                }

            }
        }
    }*/


    private var mHandler: MyHandler? = null

    internal class MyHandler(activity: SplashActivity) : Handler() {
        private val mOuter: WeakReference<SplashActivity>

        init {
            mOuter = WeakReference<SplashActivity>(activity)
        }

        override fun handleMessage(msg: Message) {
            val outer = mOuter.get()
            if (outer != null) {
                when (msg?.what) {
                    0 -> {
                        val intent = Intent()
                        //获取intent对象
                     //   intent.setClass(outer, MainActivity::class.java)  //内部类引用外部类对象
                        intent.setClass(outer, TestActivity::class.java)  //内部类引用外部类对象
                        // 获取class是使用::反射
                        outer.startActivity(intent)
                        outer.finish()
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mHandler = MyHandler(this)
        setFullScreen()
        initWelcomePage()
    }

    private fun setFullScreen() {
        //隐藏虚拟键盘
        //隐藏顶部状态栏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v = this.window.decorView
            v.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView = window.decorView
            val uiOptions = (SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or SYSTEM_UI_FLAG_IMMERSIVE_STICKY or SYSTEM_UI_FLAG_FULLSCREEN)
            decorView.systemUiVisibility = uiOptions
        }
    }

    private fun initWelcomePage() {
        val value = SharePreferenceUtil.getValue(SharePreferenceUtil.SPKey.ISFIRST, true)
        if (value) {
            SharePreferenceUtil.setValue(SharePreferenceUtil.SPKey.ISFIRST, false)
            //第一次，初始化"功能介绍"欢迎页面
            iv_welcome.visibility = View.GONE
            vp_first_welcome.visibility = View.VISIBLE
            val vpWelcomeAdapter = VpWelcomeAdapter(this)
            vp_first_welcome.adapter = vpWelcomeAdapter
        } else {
            //非第一次，初始化"即时广告"欢迎页面
            iv_welcome.visibility = View.VISIBLE
            vp_first_welcome.visibility = View.GONE
            mHandler!!.sendEmptyMessageDelayed(0, 3000)

        }

    }


}
