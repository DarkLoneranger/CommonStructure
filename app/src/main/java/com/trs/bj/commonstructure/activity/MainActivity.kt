package com.trs.bj.commonstructure.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.adapter.RcvBannerAdapter
import com.trs.bj.commonstructure.utils.ToastUtil
import com.trs.bj.commonstructure.view.RcvHeaderAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

    }

    private fun initView() {
        //   rcv_header.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false) //最后一个参数表示是否从后往前
        rcv_header.layoutManager = LinearLayoutManager(this) //最后一个参数表示是否从后往前
        val rcvBannerAdapter = RcvBannerAdapter(this)
        rcv_header.adapter= rcvBannerAdapter

        rcvBannerAdapter.run {
            val headerList: ArrayList<Any>
            headerList= arrayListOf("lalala1", "lalala2", "lalala3", "lalala4", "lalala5")
            setHeaderData(headerList)
            refreshListData(headerList)
            addListData(headerList)
            setHeaderView(View(this@MainActivity))
            setOnItemClickListener(object : RcvHeaderAdapter.OnItemClickListener{
                override fun onItemClick(v: View, position: Int) {
                        ToastUtil.showSingleLongToast("lalalala"+position)


                }
            })
        }

    }


}
