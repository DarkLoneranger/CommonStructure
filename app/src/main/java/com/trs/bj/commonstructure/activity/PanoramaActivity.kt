package com.trs.bj.commonstructure.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.vr.sdk.widgets.pano.VrPanoramaView
import com.trs.bj.commonstructure.R
import kotlinx.android.synthetic.main.activity_panorama.*

class PanoramaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panorama)
        initPanorama("vincent_world.jpg")
    }


    private fun initPanorama(fileName: String) {
        var bitmap = getBitmapFromAssets(fileName)
        var options = VrPanoramaView.Options()
        options.inputType = VrPanoramaView.Options.TYPE_MONO
        //全屏按钮
        vr_pano.setFullscreenButtonEnabled(true)
        //图片信息按钮
        vr_pano.setInfoButtonEnabled(false)
        //手指切换水平视角
        vr_pano.setTouchTrackingEnabled(false)
        //Shows or hides the transition view used to prompt the user to place their phone into a GVR viewer. This is enabled by default.
        vr_pano.setTransitionViewEnabled(false)

        //切换立体模式
        vr_pano.setStereoModeButtonEnabled(false)
        vr_pano.setFlingingEnabled(false)
        vr_pano.loadImageFromBitmap(bitmap, options)
    }

    private fun getBitmapFromAssets(fileName: String): Bitmap? {
        val am = this.resources.assets
        val inputstream = am.open(fileName)
        val image = BitmapFactory.decodeStream(inputstream)
        inputstream.close()
        return image
    }

    override fun onPause() {
        super.onPause()
        vr_pano.pauseRendering()
    }

    override fun onDestroy() {
        super.onDestroy()
        vr_pano.shutdown()
    }

    override fun onResume() {
        super.onResume()
        vr_pano.resumeRendering()
    }
}
