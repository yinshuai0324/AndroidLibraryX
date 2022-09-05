package com.ooimi.library

import activity.BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ooimi.library.adapter.HomeAdapter
import com.ooimi.library.databinding.ActivityMainBinding
import com.ooimi.library.ui.WidgetActivity
import com.ooimi.library.ui.widget.ButtonActivity
import com.ooimi.library.ui.widget.ImageActivity
import com.ooimi.library.ui.widget.LayoutActivity
import viewmodel.BaseViewModel

class MainActivity : BaseActivity<BaseViewModel, ActivityMainBinding>() {
    private var adapter: HomeAdapter? = null

    override fun initData() {
        initRecyclerView()
        adapter?.addData("控件模块")
    }

    override fun createdObserve() {
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter()
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerView.adapter = adapter
        adapter?.setOnItemClickListener { adapter, view, position ->
            val data = adapter.data[position] as String
            when (data) {
                "控件模块" -> {
                    startActivity(Intent(this, WidgetActivity::class.java))
                }
            }
        }
    }
}