package com.trs.bj.commonstructure.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.trs.bj.commonstructure.R

class FontTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle ?) {
            super.onCreate(savedInstanceState)
         //在 Android 系统中，DroidSans 是默认字体，只包含西方字符（英文），应用程序默认情况下都会调用它；
        // 而 Droid Sans Fallback 包含了东亚字符（繁体中文、简体中文、韩文、日文）。
        // 当需要显示的字符在 Droid Sans 字体中不存在（如汉字），即没有对应编码的字符时，
        // 系统会到 Droid Sans Fallback 中去寻找相应编码的字符，如果找到，则使用 Droid Sans Fallback 字体来显示它，
        // 如果仍然找不到该编码对应的字符，则系统无法在屏幕上显示出这个字符。
        //DroidSansFallback.ttf 字体文件的体积仅有 3.04MB大小，效果类似于微软雅黑字体，而且是完全免费的。
        //6.0以后，系统的默认简体中文字体已经不再是DroidSansFallback.ttf。而是NotoSansSC-Regular.otf
        //  system/fonts
        setContentView(R.layout.activity_font_test)
    }
}
