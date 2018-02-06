package com.trs.bj.commonstructure.extension

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast

/**
 * 扩展函数
 * Created by ZHAO on 2017/12/28.
 */


fun Context.toast(message: CharSequence) {
    var toast: Toast? = null
    if (toast == null) {
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
    }else{
        toast.setText(message)
    }
    toast!!.show()

}

fun Context.app():Context{
   return this.applicationContext
}

/**
 * 设置View的属性通过ObjectAnimator.ofObject()的反射机制来调用
 * @param newLoc
 */


/*var View.newPosition: PathPoint
    get():PathPoint {
        return newPosition
    }
    set(value) {
        this.newPosition=value
        this.setTranslationX(value.mX)
        this.setTranslationY(value.mY)
        Log.e("test","newPosition")
    }*/

    var View.newPosition: Int
    get():Int {
        return newPosition
    }
    set(value) {
        this.newPosition=value
        Log.e("test","newPosition"+newPosition)
    }


fun View.setPosition(value:Int ){
    Log.e("test", "Position" + value)
}

