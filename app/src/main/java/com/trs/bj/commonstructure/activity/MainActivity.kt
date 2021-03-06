package com.trs.bj.commonstructure.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.adapter.RcvBannerAdapter
import com.trs.bj.commonstructure.extension.toast
import com.trs.bj.commonstructure.view.RcvHeaderAdapter
import com.trs.bj.commonstructure.view.SuperSwipeRefreshLayout
import com.trs.bj.commonstructure.view.WrapContentLinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val headerList: ArrayList<Any>? = arrayListOf(R.mipmap.guide_1, R.mipmap.guide_2)
    val dataList: ArrayList<Any>? = arrayListOf(
    "全景图测试", "球体映射", "字体测试", "360产品展示", "水波纹进度展示",
    "贝塞尔曲线路径测试","JAVA反射获取扩展函数测试","过渡动画测试"

    /*Augmented Reality增强现实  Virtual Reality虚拟现实*/)
    var rcvBannerAdapter: RcvBannerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()


    }

    private fun initView() {
        initRefreshLayout()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        //   rcv_header.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) //最后一个参数表示是否从后往前
        //   rcv_header.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true) //最后一个参数表示是否从后往前
        rcv_header.layoutManager = WrapContentLinearLayoutManager(this) //最后一个参数表示是否从后往前
        rcvBannerAdapter = RcvBannerAdapter(this)
        rcv_header.adapter = rcvBannerAdapter
        rcvBannerAdapter?.let {
            it.setHeaderData(headerList!!)
            it.refreshListData(dataList!!)
            //     addListData(dataList)  加载更多
            it.setOnItemClickListener(object : RcvHeaderAdapter.OnItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    when (position % dataList.size) {
                        0 -> {
                            //全景图测试
                            //图片取自微博 麦田里的草帽人
                            val intent = Intent()
                            //获取intent对象
                            intent.setClass(this@MainActivity, PanoramaActivity::class.java)  //内部类引用外部类对象
                            // 获取class是使用::反射
                            this@MainActivity.startActivity(intent)
                        }
                        1 -> {
                            //球体映射测试  https://github.com/wlseu/OpenGLESTutorial
                            val intent = Intent()
                            //获取intent对象
                            intent.setClass(this@MainActivity, SphericalMappingActivity::class.java)  //内部类引用外部类对象
                            // 获取class是使用::反射
                            this@MainActivity.startActivity(intent)
                        }

                        2 -> {
                            //字体测试
                            val intent = Intent()
                            //获取intent对象
                            intent.setClass(this@MainActivity, FontTestActivity::class.java)  //内部类引用外部类对象
                            // 获取class是使用::反射
                            this@MainActivity.startActivity(intent)
                        }

                        3 -> {
                            //产品360度旋转 图片源自《故宫陶瓷馆》苹果端
                            val intent = Intent()
                            //获取intent对象
                            intent.setClass(this@MainActivity, RotateProductActivity::class.java)  //内部类引用外部类对象
                            // 获取class是使用::反射
                            this@MainActivity.startActivity(intent)
                        }

                        4 -> {
                            //水波纹测试 https://github.com/githubwing/WaveLoadingView
                            val intent = Intent()
                            //获取intent对象
                            intent.setClass(this@MainActivity, WaveLoadingActivity::class.java)  //内部类引用外部类对象
                            // 获取class是使用::反射
                            this@MainActivity.startActivity(intent)
                        }

                        5 -> {
                            //贝塞尔曲线路径测试  https://github.com/azhengyongqin/CustomAnimationFramework
                            val intent = Intent()
                            //获取intent对象
                            intent.setClass(this@MainActivity, BezierPathActivity::class.java)  //内部类引用外部类对象
                            // 获取class是使用::反射
                            this@MainActivity.startActivity(intent)
                        }

                        6 -> {
                            //JAVA反射获取扩展函数测试
                            val intent = Intent()
                            //获取intent对象
                            intent.setClass(this@MainActivity, ReflectionTestJavaActivity::class.java)  //内部类引用外部类对象
                            // 获取class是使用::反射
                            this@MainActivity.startActivity(intent)
                        }

                         7 -> {
                            //过渡动画测试 https://github.com/chuyun923/Android-Transitions
                            val intent = Intent()
                            //获取intent对象
                            intent.setClass(this@MainActivity, TransitionAnimationActivity::class.java)  //内部类引用外部类对象
                            // 获取class是使用::反射
                            this@MainActivity.startActivity(intent)
                        }

                    }

                }
            }
            )
        }
    }

    private fun initRefreshLayout() {
        swrl_newslist.setOnRefreshListener {
            rcvBannerAdapter?.let {
                val dataList1 = dataList!!
                it.refreshListData(dataList1)
            }
            mHandler?.let { it.sendEmptyMessageDelayed(0, 2000) }
        }
      /*  swrl_newslist.setOnLoadListener {
            rcvBannerAdapter?.let {
                it.addListData(dataList!!)
            }
            mHandler?.let { it.sendEmptyMessageDelayed(1, 2000) }
         }*/

    }


    private var mHandler: Handler? = @SuppressLint("HandlerLeak")
    object : Handler() {

        override fun handleMessage(msg: Message) {
            when (msg?.what) {
              0 -> {
                    if (swrl_newslist!=null && swrl_newslist.isRefreshing)
                        (swrl_newslist as SuperSwipeRefreshLayout).setRefreshing(false)

                }

                1 -> {
                 if (swrl_newslist!=null)
                     (swrl_newslist as SuperSwipeRefreshLayout).setLoading(false)
                    this@MainActivity.toast("结束加载更多")
                }

            }
        }
    }

//扩展函数
    //   fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

}
