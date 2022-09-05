package com.ooimi.library.ui.widget

import activity.BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ooimi.library.R
import com.ooimi.library.databinding.ActivityImageBinding
import viewmodel.BaseViewModel

class ImageActivity : BaseActivity<BaseViewModel, ActivityImageBinding>() {

    override fun initData() {
        title = "图片控件"
    }

    override fun createdObserve() {
    }
}