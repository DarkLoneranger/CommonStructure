package com.trs.bj.commonstructure.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log


/**
 * Created by ZHAO on 2018/1/30.
 * 解决
 * java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter positionViewHolder
 * https://stackoverflow.com/questions/31759171/recyclerview-and-java-lang-indexoutofboundsexception-inconsistency-detected-in
 */
class WrapContentLinearLayoutManager(context: Context?) : LinearLayoutManager(context) {
    //... constructor
    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (e: IndexOutOfBoundsException) {
            Log.e("probe", "meet a IOOBE in RecyclerView")
        }

    }
}