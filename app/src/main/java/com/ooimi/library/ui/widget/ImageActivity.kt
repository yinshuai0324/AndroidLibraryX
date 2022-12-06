package com.ooimi.library.ui.widget

import android.util.Log
import com.ooimi.base.activity.BaseActivity
import com.ooimi.library.databinding.ActivityImageBinding
import com.ooimi.base.viewmodel.BaseViewModel
import com.ooimi.library.R
import com.ooimi.widget.callback.LoadImageSucceedCallback
import com.ooimi.widget.image.NetworkImageView
import com.ooimi.widget.image.NetworkRoundImageView

class ImageActivity : BaseActivity<BaseViewModel, ActivityImageBinding>() {

    override fun initData() {
        title = "图片控件"
//        val view = findViewById<NetworkImageView>(R.id.networkImageView)
    }

    override fun createdObserve() {
    }
}