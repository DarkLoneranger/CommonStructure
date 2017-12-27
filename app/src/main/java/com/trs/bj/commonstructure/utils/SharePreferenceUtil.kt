package com.trs.bj.commonstructure.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by ZHAO on 2017/12/22.
 */

class SharePreferenceUtil{
    val file_name = "app_config"
    enum class SPKey {
        ISFIRST
    }

    companion object {
        @Volatile
        var sharePreferenceUtil: SharePreferenceUtil? = null

        fun getInstance(context: Context): SharePreferenceUtil {
            if (sharePreferenceUtil == null) {
                synchronized(SharePreferenceUtil::class) {
                    if (sharePreferenceUtil == null) {
                        sharePreferenceUtil = SharePreferenceUtil(context)
                    }
                }
            }
            return sharePreferenceUtil!!
        }
    }

    @Volatile
    var sharedPreferences: SharedPreferences? = null
    private constructor(context: Context){
        if (sharedPreferences == null) {
            sharedPreferences = context.applicationContext.getSharedPreferences(file_name, Context.MODE_PRIVATE)
        }
    }



    fun setValue(key: SPKey, value: Boolean) {
        sharedPreferences!!.edit()!!.putBoolean(key.name, value)!!.apply()
    }

    fun setValue(key: SPKey, value: String) {
        sharedPreferences!!.edit()!!.putString(key.name, value)!!.apply()
    }

    fun setValue(key: SPKey, value: Float) {
        sharedPreferences!!.edit()!!.putFloat(key.name, value)!!.apply()
    }

    fun setValue(key: SPKey, value: Int) {
        sharedPreferences!!.edit()!!.putInt(key.name, value)!!.apply()
    }

    fun setValue(key: SPKey, value: Long) {
        sharedPreferences!!.edit()!!.putLong(key.name, value)!!.apply()
    }

    fun getValue(key: SPKey, defaultValue: Boolean): Boolean {
        return sharedPreferences!!.getBoolean(key.name, defaultValue)
    }

    fun getValue(key: SPKey, defaultValue: String): String {
        return sharedPreferences!!.getString(key.name, defaultValue)
    }

    fun getValue(key: SPKey, defaultValue: Float): Float {
        return sharedPreferences!!.getFloat(key.name, defaultValue)
    }

    fun getValue(key: SPKey, defaultValue: Int): Int {
        return sharedPreferences!!.getInt(key.name, defaultValue)
    }

    fun getValue(key: SPKey, defaultValue: Long): Long {
        return sharedPreferences!!.getLong(key.name, defaultValue)
    }


}


