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

    var View.newPosition: Int  //不能有初始值
    get():Int {
        return newPosition
    }
    set(value) {
        this.newPosition=value
        Log.e("test","newPosition"+newPosition)
    }
/*A property with a backing field will store the value in the form of a field.
* A property without a backing field will have to store their value in other ways than directly storing it in memory.
* Note that, since extensions do not actually insert members into classes, there's no efficient way for an extension
* property to have a backing field. This is why initializers are not allowed for extension properties. */

fun View.setPosition(value:Int ){
    Log.e("test", "Position" + value)
}


/*
==========================
open class C

class D: C()

fun C.foo() = "c"

fun D.foo() = "d"

fun printFoo(c: C) {
    println(c.foo())
}

printFoo(D())
it will print"c"
==========================
class C {
    fun foo() { println("member") }
}

fun C.foo() { println("extension") }
If we call c.foo() of any c of type C, it will print "member", not "extension".
==========================
class C {
    fun foo() { println("member") }
}

fun C.foo(i: Int) { println("extension") }
可以重载
The call to C().foo(1) will print "extension".
==========================



*/

