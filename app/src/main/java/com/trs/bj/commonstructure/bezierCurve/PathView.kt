package com.trs.bj.commonstructure.bezierCurve

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View


/**
 * Created by ZHAO on 2018/2/5.
 */
class PathView: View{

    private var paint: Paint? = null

    private val path : Path
    constructor(context: Context, attrs: AttributeSet): super(context, attrs){
        initView()
        path= Path()
    }

    private fun initView() {
        paint = Paint()
        //抗锯齿
        paint!!.setAntiAlias(true)
        //防抖动
        paint!!.setDither(true)
        //设置画笔未实心
        paint!!.setStyle(Paint.Style.STROKE)
        //设置颜色
        paint!!.setColor(Color.GREEN)
        //设置画笔宽度
        paint!!.setStrokeWidth(3f)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        path.moveTo(60f, 60f)
        path.lineTo(460f, 460f)
        path.quadTo(660f, 260f, 860f, 460f) //订单
        path.cubicTo(160f, 660f, 960f, 1060f, 260f, 1260f)
        canvas!!.drawPath(path, paint)

    }
}