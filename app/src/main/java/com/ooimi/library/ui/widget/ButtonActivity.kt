package com.ooimi.library.ui.widget

import activity.BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ooimi.library.R
import com.ooimi.library.databinding.ActivityButtonBinding
import viewmodel.BaseViewModel

class ButtonActivity : BaseActivity<BaseViewModel, ActivityButtonBinding>() {
    override fun initData() {
        title = "按钮控件"
    }

    override fun createdObserve() {
    }
}