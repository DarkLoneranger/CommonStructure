package com.trs.bj.commonstructure.utils

import android.content.Context
import android.content.SharedPreferences
import com.trs.bj.commonstructure.app.App

/**
 * Created by ZHAO on 2017/12/22.
 */

class SharePreferenceUtil{

    enum class SPKey {
        ISFIRST
    }

    companion object {

        val file_name = "app_config"
        @Volatile
        var sharedPreferences: SharedPreferences? = null

        fun setValue(key: SPKey, value: Boolean) {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            sharedPreferences!!.edit()!!.putBoolean(key.name, value)!!.apply()
        }

        fun setValue(key: SPKey, value: String) {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            sharedPreferences!!.edit()!!.putString(key.name, value)!!.apply()
        }

        fun setValue(key: SPKey, value: Float) {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            sharedPreferences!!.edit()!!.putFloat(key.name, value)!!.apply()
        }

        fun setValue(key: SPKey, value: Int) {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            sharedPreferences!!.edit()!!.putInt(key.name, value)!!.apply()
        }

        fun setValue(key: SPKey, value: Long) {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            sharedPreferences!!.edit()!!.putLong(key.name, value)!!.apply()
        }

        fun getValue(key: SPKey, defaultValue: Boolean): Boolean {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            return sharedPreferences!!.getBoolean(key.name, defaultValue)
        }

        fun getValue(key: SPKey, defaultValue: String): String {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            return sharedPreferences!!.getString(key.name, defaultValue)
        }

        fun getValue(key: SPKey, defaultValue: Float): Float {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            return sharedPreferences!!.getFloat(key.name, defaultValue)
        }

        fun getValue(key: SPKey, defaultValue: Int): Int {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            return sharedPreferences!!.getInt(key.name, defaultValue)
        }

        fun getValue(key: SPKey, defaultValue: Long): Long {
            if (sharedPreferences == null) {
                sharedPreferences = App.instance.getSharedPreferences(file_name, Context.MODE_PRIVATE)
            }
            return sharedPreferences!!.getLong(key.name, defaultValue)
        }
    }




}


