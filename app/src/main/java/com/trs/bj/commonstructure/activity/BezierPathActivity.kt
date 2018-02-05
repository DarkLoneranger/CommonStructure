package com.trs.bj.commonstructure.activity

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.trs.bj.commonstructure.R
import com.trs.bj.commonstructure.bezierCurve.AnimatorPath
import com.trs.bj.commonstructure.bezierCurve.PathEvaluator
import com.trs.bj.commonstructure.bezierCurve.PathPoint
import kotlinx.android.synthetic.main.activity_bezier_path.*


class BezierPathActivity : AppCompatActivity() ,View.OnClickListener{
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_bezier -> startAnimatorPath(btn_bezier, "fab", path!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier_path)
        setPath()
        btn_bezier.setOnClickListener(this)
    }


    private var path: AnimatorPath?=null
    /*设置动画路径*/
    fun setPath() {
        path = AnimatorPath()
        path?.let {
            it.moveTo(0f, 0f)
            it.lineTo(400f, 400f)
            it.secondBesselCurveTo(600f, 200f, 800f, 400f) //订单
            it.thirdBesselCurveTo(100f, 600f, 900f, 1000f, 200f, 1200f)
         }
    }

    /**
     * 设置动画
     * @param view 使用动画的View
     * @param propertyName 属性名字
     * @param path 动画路径集合
     */
    private fun startAnimatorPath(view: View, propertyName: String, path: AnimatorPath) {
        val anim = ObjectAnimator.ofObject(this, propertyName, PathEvaluator(), *path.points.toTypedArray())
        anim.interpolator = DecelerateInterpolator()
        anim.duration = 3000
        anim.start()
        anim.addListener(object: Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                //动画结束后恢复原位
                btn_bezier.x=40f
                btn_bezier.y=40f
                btn_bezier.postInvalidate()
            }

        })
    }

    /**
     * 设置View的属性通过ObjectAnimator.ofObject()的反射机制来调用
     * @param newLoc
     */
    fun setFab(newLoc: PathPoint) {
        btn_bezier.setTranslationX(newLoc.mX)
        btn_bezier.setTranslationY(newLoc.mY)
    }


}
