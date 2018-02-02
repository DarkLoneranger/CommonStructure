package com.trs.bj.commonstructure.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLUtils;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

import com.trs.bj.commonstructure.R;
import com.trs.bj.commonstructure.openGL.Ball;
import com.trs.bj.commonstructure.openGL.IOpenGLDemo;
import com.trs.bj.commonstructure.openGL.OpenGLRenderer;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

public class SphericalMappingActivity extends Activity {
    Ball ball = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        mGLSurfaceView = new GLSurfaceView(this);
        mGLSurfaceView.setRenderer(new OpenGLRenderer(new IOpenGLDemo() {
            @Override
            public void initObject(GL10 gl) {
                //创建一个二维纹理
                ball = new Ball(5, initTexture(gl, R.mipmap.globe));
            }

            @Override
            public void initLight(GL10 gl) {
                //启动某项功能，功能列表参见https://baike.baidu.com/item/glEnable/9516351?fr=aladdin
                gl.glEnable(GL10.GL_LIGHTING);//启动灯源
                initWhiteLight(gl, GL10.GL_LIGHT0, 0.5f, 0.5f, 0.5f);
            }

            @Override
            public void DrawScene(GL10 gl) {
                //GL10-OpenGL的绘制画笔
                //glClearColor只起到Set的作用，并不Clear任何颜色
                // glClearColor 的作用是，指定刷新颜色缓冲区时所用的颜色。
                // 所以，完成一个刷新过程是要 glClearColor(COLOR) 与 glClear(GL_COLOR_BUFFER_BIT) 配合使用。
                gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
                // Clears the screen and depth buffer.
                /*GL_COLOR_BUFFER_BIT: 当前可写的颜色缓冲
                    GL_DEPTH_BUFFER_BIT: 深度缓冲
                    GL_ACCUM_BUFFER_BIT: 累积缓冲
                    GL_STENCIL_BUFFER_BIT: 模板缓冲*/
                gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
                /*GL_MODELVIEW,对模型视景矩阵堆栈应用随后的矩阵操作.
                    GL_PROJECTION,对投影矩阵应用随后的矩阵操作.
                    GL_TEXTURE,对纹理矩阵堆栈应用随后的矩阵操作.*/
                gl.glMatrixMode(GL10.GL_MODELVIEW);
                //恢复初始坐标系，用一个4×4的单位矩阵来替换当前矩阵
                gl.glLoadIdentity();
                /*glPushMatrix 函数将当前矩阵堆栈推送，通过一个，复制当前矩阵。
                将本次需要执行的缩放、平移等操作放在glPushMatrix和glPopMatrix之间。
                glPushMatrix()和glPopMatrix()的配对使用可以消除上一次的变换对本次变换的影响。
                使本次变换是以世界坐标系的原点为参考点进行。*/
                gl.glPushMatrix();
                ball.drawSelf(gl);
                gl.glPopMatrix();

            }
        }));
        setContentView(mGLSurfaceView);

        /*如果同时使用了view.setOnTouchListener()方法，则有可能存在拦截view.performClick()的响应事件，
        因为当view.OnTouchEvent()在event.getAction() == MotionEvent.ACTION_DOWN时返回false，
        系统会认为view不需要处理Touch事件，则后续的Touch事件（move、up、click）就不会被传进来，
        所以也不会触发view.performClick()，而view.setOnTouchListener()相当于是重写了view.OnTouchEvent()，
        所以在写view的TouchListener处理时，需要留意view是否存在点击事件监听，
        如果有，则在适当的位置使用view.performClick()触发点击事件。*/
        mGLSurfaceView.setOnTouchListener(onTouchListener);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mGLSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGLSurfaceView.onPause();
    }

    protected GLSurfaceView mGLSurfaceView;


    /**
     * 设置白色灯光
     *
     * @param gl
     * @param cap
     * @param posX
     * @param posY
     * @param posZ
     */
    public void initWhiteLight(GL10 gl, int cap, float posX, float posY, float posZ) {
        /*OpenGL可以同时为我们提供8个有效的光源。也就是说，我们最多可以同时启用8个光源。
        它们分别是GL_LIGHT0，GL_LIGHT1，GL_LIGHT2 ……GL_LIGHT7
        GL_LIGHT0是最特殊的一个光源，我们可以为GL_LIGHT0指定环境光成分。
        在默认情况下，GL_LIGHT0光源的颜色为白光，其他7个光源在默认情况下是没有颜色的，也即为黑色。*/
        gl.glEnable(cap);// 打开cap号灯
        //glLightfv（光源编号，光源特性，参数数据）
        // 环境光设置
        float[] ambientParams = {1.0f, 1.0f, 1.0f, 1.0f};// 光参数 RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, ambientParams, 0);

        // 散射光设置
        float[] diffuseParams = {1.0f, 1.0f, 1.0f, 1.0f};// 光参数 RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, diffuseParams, 0);

        // 反射光设置
        float[] specularParams = {1f, 1f, 1f, 1.0f};// 光参数 RGBA
        gl.glLightfv(GL10.GL_LIGHT0, GL10.GL_SPECULAR, specularParams, 0);
        //齐次坐标，当w≠0时，它表示光源处于空间中(x，y，z)处，这时的光源称为定点光源；
        // 当w＝0时，根据齐次坐标的性质，它表示光源位于无穷远处，此时光源称为定向光源，
        // 其所有光线几乎是相互平等的，如太阳。其光线方向由点（x，y，z）指向(0,0,0)。
        float[] positionParams = {posX, posY, posZ, 1};
        gl.glLightfv(cap, GL10.GL_POSITION, positionParams, 0);
    }

    /**
     * 由一个bitmap 创建一个纹理
     * bitmap的大小限制：
     *
     * @param gl
     * @param resourceId
     * @return
     */
    public int initTexture(GL10 gl, int resourceId) {
        int[] textures = new int[1];
        //n：用来生成纹理的数量
        // textures：存储纹理索引的第一个元素指针
        //根据纹理参数返回n个纹理索引。纹理名称集合不必是一个连续的整数集合。
        //glGenTextures就是用来产生你要操作的纹理对象的索引的，比如你告诉OpenGL，我需要5个纹理对象，它会从没有用到的整数里返回5个给你）

        gl.glGenTextures(1, textures, 0);
        int currTextureId = textures[0];
        //glBindTexture实际上是改变了OpenGL的这个状态，它告诉OpenGL下面对纹理的任何操作都是对它所绑定的纹理对象的，
        //比如glBindTexture(GL_TEXTURE_2D, 1) 告诉OpenGL下面代码中对2D纹理的任何设置都是针对索引为1的纹理的。
        gl.glBindTexture(GL10.GL_TEXTURE_2D, currTextureId);
        //https://www.khronos.org/registry/OpenGL-Refpages/gl4/html/glTexParameter.xhtml
        //Returns the value of the texture element that is nearest (in Manhattan distance) to the specified texture coordinates.
        //曼哈顿距离——两点在南北方向上的距离加上在东西方向上的距离
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);  //缩小
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);  //放大
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

        InputStream is = this.getResources().openRawResource(resourceId);
        Bitmap bitmapTmp;
        try {
            bitmapTmp = BitmapFactory.decodeStream(is);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmapTmp, 0);
        bitmapTmp.recycle();
        return currTextureId;
    }

    /**
     * 初始化白色材质
     * <p>
     * 材质为白色时什么颜色的光照在上面就将体现出什么颜色
     *
     * @param gl
     */
    private void initMaterial(GL10 gl) {

        // 环境光为白色材质
        float ambientMaterial[] = {1.0f, 1.0f, 1.0f, 1.0f};
        //参数1 face的取值可以是GL_FRONT、GL_BACK或GL_FRONT_AND_BACK，指出材质属性将应用于物体的哪面。
        //参数2 pname指出要设置的哪种材质属性。param为要设置的属性值，是一个指向数组的指针（向量版本）或一个数值（非向量版本）。
        // 只有设置参数值是GL_SHININESS时，才能使用非向量版本。

        //GL_AMBIENT   材质的环境颜色
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_AMBIENT, ambientMaterial, 0);

        // 散射光为白色材质
        //GL_DIFFUSE   材质的散射颜色
        float diffuseMaterial[] = {1.0f, 1.0f, 1.0f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_DIFFUSE, diffuseMaterial, 0);

        // 高光材质为白色
        //GL_SPECULAR 材质的镜面反射颜色
        float specularMaterial[] = {1f, 1f, 1f, 1.0f};
        gl.glMaterialfv(GL10.GL_FRONT_AND_BACK, GL10.GL_SPECULAR, specularMaterial, 0);
        //GL_SHININESS 镜面反射指数
        gl.glMaterialf(GL10.GL_FRONT_AND_BACK, GL10.GL_SHININESS, 100.0f);
    }




    private OnTouchListener onTouchListener = new OnTouchListener() {
        float lastX, lastY;

        private int mode = 0; // ���ص�ĸ���

        float oldDist = 0;

        @SuppressLint("ClickableViewAccessibility")
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mode = 1;
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                mode += 1;

                oldDist = caluDist(event);

                break;

            case MotionEvent.ACTION_POINTER_UP:
                mode -= 1;
                break;

            case MotionEvent.ACTION_UP:
                mode = 0;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode >= 2) {        //多点触摸为缩放
                    float newDist = caluDist(event);
                    if (Math.abs(newDist - oldDist) > 2f) {
                        zoom(newDist, oldDist);
                    }
                } else {        //单点触摸为旋转
                    float dx = event.getRawX() - lastX;
                    float dy = event.getRawY() - lastY;

                    float a = 180.0f / 320;
                    ball.mAngleX += dx * a;
                    ball.mAngleY += dy * a;
                }
                break;
            }

            lastX = (int) event.getRawX();
            lastY = (int) event.getRawY();
            return true;
        }
    };

    public void zoom(float newDist, float oldDist) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();

        float px = displayMetrics.widthPixels;
        float py = displayMetrics.heightPixels;

        ball.zoom += (newDist - oldDist) * (ball.maxZoom - ball.minZoom) / Math.sqrt(px * px + py * py) / 4;

        if (ball.zoom > ball.maxZoom) {
            ball.zoom = ball.maxZoom;
        } else if (ball.zoom < ball.minZoom) {
            ball.zoom = ball.minZoom;
        }
    }

    public float caluDist(MotionEvent event) {
        float dx = event.getX(0) - event.getX(1);
        float dy = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
