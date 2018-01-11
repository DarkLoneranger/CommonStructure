package com.trs.bj.commonstructure.utils

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * Created by ZHAO on 2017/12/25.
 */
class ScreenUtil{

    companion object {
        fun getScreenHeight(context: Context) :Int{
            val systemService = context . getSystemService (Context.WINDOW_SERVICE) as WindowManager
            val displayMetrics = DisplayMetrics()
            systemService.defaultDisplay.getMetrics(displayMetrics)
            return  displayMetrics.heightPixels
        }

        fun getScreenWidth(context: Context) :Int{
           // val systemService = context . getSystemService (Context.WINDOW_SERVICE) as WindowManager
            val systemService = context . getSystemService (Context.WINDOW_SERVICE)
            if (systemService is WindowManager){
                val displayMetrics = DisplayMetrics()
                systemService.defaultDisplay.getMetrics(displayMetrics)
                return  displayMetrics.widthPixels
            }else{
                return 0
            }
        }

        fun px2dp(context: Context, pxValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (pxValue / scale + 0.5f).toInt()
        }

        fun dp2px(context: Context, dipValue: Float): Int {
            val scale = context.resources.displayMetrics.density
            return (dipValue * scale + 0.5f).toInt()
        }
    }



  /*  public static int getScreenHeight(Context context)
    {
        WindowManager wm =(WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }*/
}