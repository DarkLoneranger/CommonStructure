package com.trs.bj.commonstructure.openGL;

import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class OpenGLRenderer implements Renderer {

    private final IOpenGLDemo openGLDemo;

    public OpenGLRenderer(IOpenGLDemo demo) {
        openGLDemo = demo;
    }

    @Override
    //onSurfaceCreated方法主要用于执行一些初始化操作
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //gl.glDisable用于禁用OpenGL某方面的特性，该处表示关闭抗抖动，可以提高性能
        gl.glDisable(GL10.GL_DITHER);
        //设置OpenGL清屏所用的颜色，四个参数分别代表红、绿、蓝和透明度值，范围为0-1
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
        //设置阴影模式为平滑模式
        gl.glShadeModel(GL10.GL_SMOOTH);
        //glClearDepthf指定一个深度值。该值将被用于glClear函数清理深度缓冲区。
        // 被glClearDepthf指明的值会被clamp至区间[0, 1]（小于0则置为0，大于1则置为1）。
        gl.glClearDepthf(1.0f);
        //设置深度测试的类型，此处为如果输入的深度值小于或等于参考值，则通过
        gl.glDepthFunc(GL10.GL_LEQUAL);
        //启用某方面的性能，此处为启动深度测试，负责跟踪每个物体在Z轴上的深度，避免后面的物体遮挡前面的物体
        gl.glEnable(GL10.GL_DEPTH_TEST);
        //该方法用于修正，本处用于设置对透视进行修正
        gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);

        if (openGLDemo != null) {
            openGLDemo.initLight(gl);

            openGLDemo.initObject(gl);
        }

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
            //当SurfaceView大小改变时回调，通常用于初始化3D场景
            // Sets the current view port to the new size.
            ////设置3D视窗的位置与大小
            gl.glViewport(0, 0, width, height);
            // Select the projection matrix
            //设置矩阵模式为投影矩阵，这意味着越远的东西看起来越小，
            gl.glMatrixMode(GL10.GL_PROJECTION);

            //设置透视投影的空间大小，前两个参数用于设置X轴的最小值与最大值
            //中间两个参数用于设置y轴的最小值最大值，后两个参数用于设置Z轴的最小值最大值
            //   float ratio = (float) width / height;
            //    gl.glFrustumf(-ratio, ratio, -1, 1, 1, 10);

            // Reset the projection 投影 matrix
            //OpenGL为我们提供了一个非常简单的恢复初始坐标系的手段，那就是调用glLoadIdentity()命令。
            // 该命令是一个无参的无值函数，其功能是用一个4×4的单位矩阵来替换当前矩阵，实际上就是对当前矩阵进行初始化。
            // 无论以前进行了多少次矩阵变换，在该命令执行后，当前矩阵均恢复成一个单位矩阵，即相当于没有进行任何矩阵变换状态。
            gl.glLoadIdentity();
            // Calculate the aspect ratio of the window
            //fovy是眼睛上下睁开的幅度，角度值，值越小，视野范围越狭小（眯眼），值越大，视野范围越宽阔（睁开铜铃般的大眼）；
            // zNear表示近裁剪面到眼睛的距离，zFar表示远裁剪面到眼睛的距离，注意zNear和zFar不能设置设置为负值（你怎么看到眼睛后面的东西）。
            //aspect表示裁剪面的宽w高h比，这个影响到视野的截面有多大。
            GLU.gluPerspective(gl, 45.0f, (float) width / (float) height, 0.1f, 100.0f);
            // Select the modelview matrix
            //GL_PROJECTION 投影, GL_MODELVIEW 模型视图, GL_TEXTURE 纹理
            gl.glMatrixMode(GL10.GL_MODELVIEW);
            // Reset the modelview matrix
            gl.glLoadIdentity();
        }


    @Override
    //用于绘制3D图形
    public void onDrawFrame(GL10 gl) {
        if (openGLDemo != null) {
            openGLDemo.DrawScene(gl);
        }
        /*   //清楚屏幕缓存和深度缓存(一般为必须设置的)
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT|GL10.GL_DEPTH_BUFFER_BIT);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);//启用顶点坐标数组
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);//启用顶点颜色数组
        gl.glMatrixMode(GL10.GL_MODELVIEW);设置矩阵模式为模型视图矩阵
        gl.glLoadIdentity();//相当于reset()方法，用于初始化单位矩阵
        gl.glTranslatef(-0.32f, 0.35f, -1.1f);//移动绘图中心  */
    }







}
