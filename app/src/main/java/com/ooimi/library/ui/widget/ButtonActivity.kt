package com.ooimi.library.ui.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ooimi.library.R

class ButtonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)
        title = "按钮控件"
    }
}