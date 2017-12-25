package com.trs.bj.commonstructure

import android.app.Application
import com.trs.bj.commonstructure.Utils.SharePreferenceUtil

/**
 * Created by ZHAO on 2017/12/22.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharePreferenceUtil.getInstance(this)
    }


}