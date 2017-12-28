package com.trs.bj.commonstructure.utils

import android.content.Context
import android.widget.Toast
import com.trs.bj.commonstructure.app.App


/**
 * Created by ZHAO on 2017/12/27.
 */
class ToastUtil private constructor() {


companion object {
    private var mToast: Toast? = null
    fun getSingletonToast(text: String): Toast {
        if (mToast == null) {
            mToast = Toast.makeText(App.instance, text, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(text)
        }
        return mToast!!
    }

     fun getSingletonToast(resId: Int): Toast {
        if (mToast == null) {
            mToast = Toast.makeText(App.instance, resId, Toast.LENGTH_SHORT)
        } else {
            mToast!!.setText(resId)
        }
        return mToast!!
    }

    fun getSingleLongToast(resId: Int): Toast {
        if (mToast == null) {
            mToast = Toast.makeText(App.instance, resId, Toast.LENGTH_LONG)
        } else {
            mToast!!.setText(resId)
        }
        return mToast!!
    }

    fun getSingleLongToast(text: String): Toast {
        if (mToast == null) {
            mToast = Toast.makeText(App.instance, text, Toast.LENGTH_LONG)
        } else {
            mToast!!.setText(text)
        }
        return mToast!!
    }

    fun getToast(resId: Int): Toast {
        return Toast.makeText(App.instance, resId, Toast.LENGTH_SHORT)
    }

    fun getToast(text: String): Toast {
        return Toast.makeText(App.instance, text, Toast.LENGTH_SHORT)
    }

    fun getLongToast(resId: Int): Toast {
        return Toast.makeText(App.instance, resId, Toast.LENGTH_LONG)
    }

    fun getLongToast(text: String): Toast {
        return Toast.makeText(App.instance, text, Toast.LENGTH_LONG)
    }

    fun showSingletonToast(resId: Int) {
        getSingletonToast(resId).show()
    }


    fun showSingletonToast(text: String) {
        getSingletonToast(text).show()
    }

    fun showSingleLongToast(resId: Int) {
        getSingleLongToast(resId).show()
    }


    fun showSingleLongToast(text: String) {
        getSingleLongToast(text).show()
    }

    fun showToast(resId: Int) {
        getToast(resId).show()
    }

    fun showToast(text: String) {
        getToast(text).show()
    }

    fun showLongToast(resId: Int) {
        getLongToast(resId).show()
    }

    fun showLongToast(text: String) {
        getLongToast(text).show()
    }
}

   
} 