package com.trs.bj.commonstructure.extension

import android.content.Context
import android.widget.Toast

/**
 * 扩展函数
 * Created by ZHAO on 2017/12/28.
 */


fun Context.toast(message: CharSequence) {
    var toast: Toast? = null
    if (toast == null) {
        toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    }else{
        toast.setText(message)
    }
    toast!!.show()

}

fun Context.app():Context{
   return this.applicationContext
}

