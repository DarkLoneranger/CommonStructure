package com.trs.bj.commonstructure.app

import android.app.Application
import android.content.Context
import com.trs.bj.commonstructure.utils.SharePreferenceUtil

/**
 * Created by ZHAO on 2017/12/22.
 */
class App : Application() {  //应用名字需要在清单文件中配置
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set  //设置set方法私有
    }




}