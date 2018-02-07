package com.trs.bj.commonstructure.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.ImageView

import com.trs.bj.commonstructure.R

import java.lang.reflect.Field
import java.lang.reflect.Method

class ReflectionTestJavaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_java)
        val iv = findViewById<ImageView>(R.id.iv)

        //1.获取Class对象
        var stuClass: Class<*>? = null
        try {
            stuClass = Class.forName("android.view.View")
        } catch (e: Exception) {
            Log.e("test", e.toString())
        }

        var f: Field? = null
        try {
            f = stuClass!!.getField("newPosition")
        } catch (e: Exception) {
            Log.e("test", e.toString())
        }

        var obj: Any? = null//产生Student对象--》Student stu = new Student();
        try {
            obj = stuClass!!.getConstructor().newInstance()
        } catch (e: Exception) {
            Log.e("test", e.toString())
        }

        //为字段设置值
        try {
            f!!.set(obj, 555)//为Student对象中的name属性赋值--》stu.name = "刘德华"
        } catch (e: Exception) {
            Log.e("test", e.toString())
        }

        try {
            val setPosition = stuClass!!.getDeclaredMethod("setPosition", Int::class.javaPrimitiveType)
            setPosition.isAccessible = true//解除私有限定
            try {
                setPosition.invoke(obj, 20)
            } catch (e: Exception) {
                Log.e("test", e.toString())
            }

        } catch (e: Exception) {
            Log.e("test", e.toString())
        }

        /*
        在java代码通过反射获取kotlin扩展函数或者扩展属性报错
        NoSuchFieldException: newPosition
        NoSuchMethodException: <init> []
         java.lang.NoSuchMethodException: setPosition [int]
*/

        /*  https://discuss.kotlinlang.org/t/how-do-extension-functions-work/609

       As the docs say: "Extension functions are resolved statically", i.e. they are normal static methods
       bearing no connection with the class they are extending, other than taking an instance of this class
       as a parameters.
       http://confluence.jetbrains.com/display/Kotlin/Extension

       So, this is just a trick the Kotlin compiler is playing without actually modifying the targeted class?
       There's no way to address the extension function through reflection or from Java code?

       The target classes are not modified. Extensions are visible from Java reflection as static methods
       in the classes they are defined in (.i.e. in package-classes for top-level extension functions).*/


        /*
       Extension fucntions and properties are not added to existing class for real. Kotlin compiler
       allows you to reference them AS IF they were a part of the class.
       If you write in Kotlin a function 'setPosition' in a Kotlin file named 'Funs.kt':
       //in file Funs.kt
       fun View.setPosition(value: Int) {
             //smth
       }
       The compiler will make it a FunsKt.class with a static method 'setPosition' inside.
       public final class FunsKt {
             public static void setPosition(View $receiver, int value) {
                 //smth
             }
       }
       It is just Kotlin compiler magic that it allows you to use it as if it was a part of the
       View class (notice that because of that you cannot access private/protected values
       from extension funtions). From java you must use it as it is.

       To obtain Method setPosition do that:
       Class c = Class.forName("[your package].[kotlin file name + "Kt"]");
       Method m = c.getMethod("setPosition", View.class, int.class);
       Extension properties work in a simmilar way. No real variable is created - only two static
       methods (getter and setter). These method don't have a real variable in which they store value,
       but they calculate this value on the go. You cannot obtain them as a Field object but you can
       get them as methods in the very same way as setPosition.
       */


    }
}
