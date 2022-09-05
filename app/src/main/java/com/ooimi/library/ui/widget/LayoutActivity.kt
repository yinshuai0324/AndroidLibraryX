package com.ooimi.library.ui.widget

import activity.BaseActivity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ooimi.library.R
import com.ooimi.library.databinding.ActivityLayoutBinding
import viewmodel.BaseViewModel

class LayoutActivity : BaseActivity<BaseViewModel, ActivityLayoutBinding>() {

    override fun initData() {
        title = "容器控件"
    }

    override fun createdObserve() {
    }
}