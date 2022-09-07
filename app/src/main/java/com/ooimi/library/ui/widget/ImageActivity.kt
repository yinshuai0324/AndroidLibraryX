package com.ooimi.library.ui.widget

import com.ooimi.base.activity.BaseActivity
import com.ooimi.library.databinding.ActivityImageBinding
import com.ooimi.base.viewmodel.BaseViewModel

class ImageActivity : BaseActivity<BaseViewModel, ActivityImageBinding>() {

    override fun initData() {
        title = "图片控件"
    }

    override fun createdObserve() {
    }
}