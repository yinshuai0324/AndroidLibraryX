package com.ooimi.library.ui.widget

import android.util.Log
import com.ooimi.base.activity.BaseActivity
import com.ooimi.library.databinding.ActivityButtonBinding
import com.ooimi.base.viewmodel.BaseViewModel

class ButtonActivity : BaseActivity<BaseViewModel, ActivityButtonBinding>() {
    override fun initData() {
        title = "按钮控件"
        viewBinding.disableBtn.setOnClickListener {
            Log.i("===>>>", "点击事件响应")
        }
        viewBinding.disableBtn.onDisableClickEvent={
            Log.i("===>>>", "禁用点击事件响应")
        }
        viewBinding.disableBtn.onEnableClickEvent={
            Log.i("===>>>", "可用点击事件响应")
        }
    }

    override fun createdObserve() {
    }
}