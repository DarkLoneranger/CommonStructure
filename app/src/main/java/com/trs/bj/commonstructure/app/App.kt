package com.trs.bj.commonstructure.app

import android.app.Application
import com.trs.bj.commonstructure.utils.SharePreferenceUtil

/**
 * Created by ZHAO on 2017/12/22.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharePreferenceUtil.getInstance(this)
    }


}