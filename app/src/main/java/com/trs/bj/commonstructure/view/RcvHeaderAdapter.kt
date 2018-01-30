package com.trs.bj.commonstructure.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup


/**
 * 使用方法:
 * 1 继承 RcvHeaderAdapter
 * 2 复写抽象方法
 * 3 ViewHolder分别继承ItemViewHolder、HeaderViewHolder
 * 4 设置数据setHeaderData、refreshListData、addListData
 *
 */

abstract class RcvHeaderAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private val ITEM_HEADER = 0
    private val ITEM_NORMAL = 1

    private var headerData: ArrayList<Any>? = null
    private var listData: ArrayList<Any>? = null

    private var context: Context? = null

    constructor(context: Context) {
        this.context = context
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (headerData!=null && headerData!!.size>0 && position == 0) {
            return ITEM_HEADER
        } else {
            return ITEM_NORMAL
        }
    }

    override fun getItemCount(): Int {
        return if (headerData == null && headerData!!.size == 0) (if (listData == null) 0 else listData!!.size) else 1 + (if (listData == null) 0 else listData!!.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        var holder: RecyclerView.ViewHolder?

        when (viewType) {
            ITEM_HEADER -> {
                holder = bindHeaderLayoutResource()
            }

            ITEM_NORMAL -> {
                holder = bindItemLayoutResource()
            }

            else -> {
                holder = bindItemLayoutResource()
            }
        }

        return holder
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is HeaderViewHolder -> {
                if (headerData != null) {
                    onBindHeaderViewHolder(holder, headerData)
                }
            }

            is ItemViewHolder -> {
                val realPosition = if (headerData == null && headerData!!.size == 0) position else position - 1
                if (listData != null) {
                    onBindItemViewHolder(holder, realPosition, listData)
                }

                holder.view?.setOnClickListener { v ->
                    if (clickListener != null)
                        clickListener?.onItemClick(v, realPosition)
                 }

            }
        }
    }




    /**
     * 设置顶部轮播图数据
     */
    fun setHeaderData(headerData: ArrayList<Any>) {
        if (this.headerData == null) {
            this.headerData = ArrayList<Any>()
        }
        this.headerData!!.clear()
        this.headerData = headerData
        notifyItemChanged(0)
    }

    /**
     * 刷新列表数据
     */
    fun refreshListData(dataList: ArrayList<Any>) {
        if (this.listData == null) {
            this.listData = ArrayList<Any>()
        }
        this.listData?.clear()
        if (dataList != null && dataList.size>0) {
            this.listData!!.addAll(dataList)
            notifyItemRangeChanged(1, this.listData!!.size)
        }
        //positionStart Position of the first item that has changed
        //itemCount Number of items that have changed
    }

    /**
     * 添加列表数据
     */
    fun addListData(dataList: ArrayList<Any>) {
        if (this.listData == null) {
            this.listData = ArrayList<Any>()
        }
        if (dataList != null && dataList.size > 0) {
            this.listData?.addAll(dataList)
            notifyItemRangeChanged(1, this.listData!!.size)
        }
    }

    /**
     * 设置顶部轮播图的布局文件
     */
    abstract fun bindHeaderLayoutResource(): HeaderViewHolder

    /**
     * 设置普通条目的布局文件
     */
    abstract fun bindItemLayoutResource(): ItemViewHolder

    /**
     * 初始化顶部轮播图
     */
    abstract fun onBindHeaderViewHolder(holder: HeaderViewHolder?, headerData: ArrayList<Any>?)

    /**
     * 初始化条目轮播图
     */
    abstract fun onBindItemViewHolder(holder: ItemViewHolder?, position: Int, listData: ArrayList<Any>?)


    var clickListener: OnItemClickListener? = null
    /**
     * 条目点击
     */
    fun setOnItemClickListener(clickListener: OnItemClickListener) {
        this.clickListener = clickListener
    }

    interface OnItemClickListener {
        fun onItemClick(v: View, position: Int);
    }


    /**
     * 列表item
     * open
     */
    open class ItemViewHolder : RecyclerView.ViewHolder {
        var view: View? = null

        constructor(view: View) : super(view) {
            this.view = view
            initView(view)
        }

        open fun initView(view: View) {}


    }

    /**
     * 轮播图View
     */
    open class HeaderViewHolder : RecyclerView.ViewHolder {
        var view: View? = null

        constructor(view: View) : super(view) {
            this.view = view
            initView(view)
        }

        open fun initView(view: View) {}
    }
}