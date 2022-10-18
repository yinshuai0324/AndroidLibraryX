package com.ooimi.library.ui.widget

import android.util.Log
import com.ooimi.base.activity.BaseActivity
import com.ooimi.library.databinding.ActivityImageBinding
import com.ooimi.base.viewmodel.BaseViewModel
import com.ooimi.library.R
import com.ooimi.widget.callback.LoadImageSucceedCallback
import com.ooimi.widget.image.NetworkRoundImageView

class ImageActivity : BaseActivity<BaseViewModel, ActivityImageBinding>() {

    override fun initData() {
        title = "图片控件"
        val view = findViewById<NetworkRoundImageView>(R.id.roundImageView)
        view.setOnImageLoadListener(object : LoadImageSucceedCallback() {
            override fun onLoadSucceed() {
                Log.i("===>>>", "加载图片成功...")
            }
        })
    }

    override fun createdObserve() {
    }
}