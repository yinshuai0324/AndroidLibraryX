package com.ooimi.library.ui.widget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ooimi.library.R

class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout)
        title = "容器控件"
    }
}