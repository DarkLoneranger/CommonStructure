package com.trs.bj.commonstructure.openGL;

import javax.microedition.khronos.opengles.GL10;

public interface IOpenGLDemo {
    public void DrawScene(GL10 gl);

    public void initLight(GL10 gl);

    public void initObject(GL10 gl);
}
