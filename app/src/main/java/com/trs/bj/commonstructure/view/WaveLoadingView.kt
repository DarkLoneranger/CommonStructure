package com.trs.bj.commonstructure.view


import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class WaveLoadingView : View {

    private val mPaint: Paint
    private val mTextPaint: Paint
    private val mCanvas: Canvas
    private val mBitmap: Bitmap
    private var y: Int = 0
    private var x: Int = 0

    private val mMode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    private val mPath: Path
    private var isToLeft: Boolean = false
    //    private int y;
    private var mWidth: Int = 0
    private var mHeight: Int = 0
    private var mPercent: Int = 0

    constructor(context: Context) : this(context, null) {
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {

    }

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        mPaint = Paint()
        mPaint.isAntiAlias = true

        mTextPaint = Paint()
        mTextPaint.isAntiAlias = true
        mTextPaint.textSize = 60f

        mPath = Path()

        mBitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        mCanvas = Canvas(mBitmap)

    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthSize = View.MeasureSpec.getSize(widthMeasureSpec)
        val widthMode = View.MeasureSpec.getMode(widthMeasureSpec)
        val heightSize = View.MeasureSpec.getSize(heightMeasureSpec)
        val heightMode = View.MeasureSpec.getMode(heightMeasureSpec)
        if (widthMode == View.MeasureSpec.EXACTLY) {
            mWidth = widthSize
        }


        if (heightMode == View.MeasureSpec.EXACTLY) {
            mHeight = heightSize
        }

        y = mHeight
        setMeasuredDimension(mWidth, mHeight)

    }

    override fun onDraw(canvas: Canvas) {
        //x初始值为0
        //isleft 初始值为false
        //0----mWidth / 3保留上一个值
        if (x > mWidth / 3) {  //到达最右边之后开始往左波动
            isToLeft = true
        } else if (x < 0) {  //到达最左边之后开始往右波动
            isToLeft = false
        }

        if (isToLeft) {
            x = x - 1
        } else {
            x = x + 1
        }
        mPath.reset()
        y = ((1 - mPercent / 100f) * mHeight).toInt()
        //1 左上顶点
        mPath.moveTo(0f, y.toFloat())
        //控制点1，控制点2，终点
        //2 终点为右上顶点
        val controlX = mWidth / 3
        val controlY = controlX
        //mPath.cubicTo((controlX + x * 2).toFloat(), (controlY + y).toFloat(), (controlX + x * 2).toFloat(), (y - controlY).toFloat(), mWidth.toFloat(), y.toFloat())
        mPath.cubicTo((0 + x * 3).toFloat(), (controlY + y).toFloat(), (0 + x * 3).toFloat(), (y - controlY).toFloat(), mWidth.toFloat(), y.toFloat())
        //3 右下顶点
        mPath.lineTo(mWidth.toFloat(), mHeight.toFloat())
        //4 左下顶点
        mPath.lineTo(0f, mHeight.toFloat())
        mPath.close()

        //清除掉图像 不然path会重叠
        mBitmap.eraseColor(Color.parseColor("#00000000"))
        //1、先绘制目标图-圆形
        // PorterDuff dst
        mPaint.color = Color.parseColor("#88dddddd")
        mCanvas.drawCircle((mWidth / 2).toFloat(), (mHeight / 2).toFloat(), (mWidth / 2).toFloat(), mPaint)
        //2、设置模式
        mPaint.xfermode = mMode
        //3、后绘制原图-已有进度
        //PorterDuff src
        mPaint.color = Color.parseColor("#8800ff66")//已有进度 src 的波纹颜色
        mCanvas.drawPath(mPath, mPaint)
        //4、还原模式
        mPaint.xfermode = null

        canvas.drawBitmap(mBitmap, 0f, 0f, null)

        val str = mPercent.toString() + " %"
        val txtLength = mTextPaint.measureText(str)
        canvas.drawText(str, mWidth / 2 - txtLength / 2, (mHeight / 2 + 15).toFloat(), mTextPaint)

        postInvalidateDelayed(10) //重绘控件的间隔时长

    }


    fun setPercent(percent: Int) {
        mPercent = percent
    }

}
