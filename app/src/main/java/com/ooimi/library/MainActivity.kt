package com.ooimi.library

import activity.BaseActivity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.ooimi.library.adapter.HomeAdapter
import com.ooimi.library.databinding.ActivityMainBinding
import com.ooimi.library.ui.WidgetActivity
import com.ooimi.base.viewmodel.BaseViewModel
import com.ooimi.network.NetworkManage
import com.ooimi.network.request.ApiRequest

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {
    private var adapter: HomeAdapter? = null

    override fun initData() {
        initRecyclerView()
        adapter?.addData("控件模块")
        adapter?.addData("网络请求")
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
                "网络请求" -> {

                }
            }
        }
    }
}