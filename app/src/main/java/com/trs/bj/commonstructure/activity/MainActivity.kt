package com.trs.bj.commonstructure.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.adapter.RcvBannerAdapter
import com.trs.bj.commonstructure.extension.app
import com.trs.bj.commonstructure.extension.toast
import com.trs.bj.commonstructure.utils.ToastUtil
import com.trs.bj.commonstructure.view.RcvHeaderAdapter
import com.trs.bj.commonstructure.view.SuperSwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
        rcv_header.layoutManager = LinearLayoutManager(this) //最后一个参数表示是否从后往前
        val rcvBannerAdapter = RcvBannerAdapter(this)
        rcv_header.adapter = rcvBannerAdapter
        rcvBannerAdapter.run {
            val headerList: ArrayList<Any>
            headerList = arrayListOf(R.mipmap.guide_1, R.mipmap.guide_2, R.mipmap.guide_3, R.mipmap.guide_4, R.mipmap.guide)
            setHeaderData(headerList)
            val dataList: ArrayList<Any>
            dataList = arrayListOf("lalala1", "lalala2", "lalala3", "lalala4", "lalala5")
            refreshListData(dataList)
            addListData(dataList)
            setOnItemClickListener(object : RcvHeaderAdapter.OnItemClickListener {
                override fun onItemClick(v: View, position: Int) {
                    ToastUtil.showSingleLongToast("lalalala" + position)
                    this@MainActivity.app().toast("ceshi")
                }
            })
        }
    }

    private fun initRefreshLayout() {
        swrl_newslist.setOnRefreshListener{ mHandler?.let { it.sendEmptyMessageDelayed(0, 5000) }}
        swrl_newslist.setOnLoadListener {
            this.toast("开始加载更多")
         mHandler?.let { it.sendEmptyMessageDelayed(1, 5000) }
         }

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
