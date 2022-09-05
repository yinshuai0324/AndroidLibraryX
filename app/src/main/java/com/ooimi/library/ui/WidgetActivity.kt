package com.ooimi.library.ui

import activity.BaseActivity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.ooimi.library.adapter.HomeAdapter
import com.ooimi.library.databinding.ActivityWidgetBinding
import com.ooimi.library.ui.widget.ButtonActivity
import com.ooimi.library.ui.widget.ImageActivity
import com.ooimi.library.ui.widget.LayoutActivity
import com.ooimi.base.viewmodel.BaseViewModel

class WidgetActivity : BaseActivity<BaseViewModel,ActivityWidgetBinding>() {
    private var adapter: HomeAdapter? = null

    override fun initData() {
        title = "控件模块"
        initRecyclerView()
        adapter?.addData("图片控件")
        adapter?.addData("按钮控件")
        adapter?.addData("容器控件")
    }

    override fun createdObserve() {
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter()
        viewBinding.recyclerView?.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerView?.adapter = adapter

        adapter?.setOnItemClickListener { adapter, view, position ->
            val data = adapter.data[position] as String
            when (data) {
                "图片控件" -> {
                    startActivity(Intent(this, ImageActivity::class.java))
                }
                "按钮控件" -> {
                    startActivity(Intent(this, ButtonActivity::class.java))
                }
                "容器控件" -> {
                    startActivity(Intent(this, LayoutActivity::class.java))
                }
            }
        }
    }
}