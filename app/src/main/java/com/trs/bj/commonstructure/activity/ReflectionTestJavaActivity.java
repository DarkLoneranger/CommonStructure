package com.trs.bj.commonstructure.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.trs.bj.commonstructure.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTestJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_java);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        //1.获取Class对象
        Class stuClass = null;
        try {
            stuClass = Class.forName("android.view.View");
        } catch (Exception e) {
            Log.e("test", e.toString());
        }
        Field f = null;
        try {
            f = stuClass.getField("newPosition");
        } catch (Exception e) {
            Log.e("test", e.toString());
        }
        Object obj = null;//产生Student对象--》Student stu = new Student();
        try {
            obj = stuClass.getConstructor().newInstance();
        } catch (Exception e) {
            Log.e("test", e.toString());
        }
        //为字段设置值
        try {
            f.set(obj, 555);//为Student对象中的name属性赋值--》stu.name = "刘德华"
        } catch (Exception e) {
            Log.e("test", e.toString());
        }

        try {
            Method setPosition = stuClass.getDeclaredMethod("setPosition", int.class);
            setPosition.setAccessible(true);//解除私有限定
            try {
                setPosition.invoke(obj, 20);
            } catch (Exception e) {
                Log.e("test", e.toString());
            }

        } catch (Exception e) {
            Log.e("test", e.toString());
        }

        /*
        在java代码通过反射获取kotlin扩展函数或者扩展属性报错
        NoSuchFieldException: newPosition
        NoSuchMethodException: <init> []
         java.lang.NoSuchMethodException: setPosition [int]
        */

        /*
        * https://discuss.kotlinlang.org/t/how-do-extension-functions-work/609
        *
        * As the docs say: "Extension functions are resolved statically", i.e. they are normal static methods
        * bearing no connection with the class they are extending, other than taking an instance of this class
        * as a parameters.
        * http://confluence.jetbrains.com/display/Kotlin/Extension
        *
        * So, this is just a trick the Kotlin compiler is playing without actually modifying the targeted class?
        * There's no way to address the extension function through reflection or from Java code?
        *
        * The target classes are not modified. Extensions are visible from Java reflection as static methods
        * in the classes they are defined in (.i.e. in package-classes for top-level extension functions).
        * */








    }
}
