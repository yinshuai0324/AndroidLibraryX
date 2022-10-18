package com.ooimi.library

import com.ooimi.base.activity.BaseActivity
import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ooimi.library.adapter.HomeAdapter
import com.ooimi.library.databinding.ActivityMainBinding
import com.ooimi.library.ui.WidgetActivity
import com.ooimi.library.ui.base.MainBaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>() {
    private var adapter: HomeAdapter? = null

    override fun initData() {
        initRecyclerView()
        adapter?.addData("控件模块")
        adapter?.addData("网络请求")
        adapter?.addData("Base模块")
    }

    override fun createdObserve() {
        flow<String> {
            Log.i("===>>>", "onFlow:${Thread.currentThread().name}")
            delay(1000)
            emit("data")
            Log.i("===>>>", "onFlow end:${Thread.currentThread().name}")
        }.flowOn(Dispatchers.IO).onStart {
            Log.i("===>>>", "onStart:${Thread.currentThread().name}")
        }.onCompletion {
            Log.i("===>>>", "onCompletion:${Thread.currentThread().name}")
        }.onEach {
            Log.i("===>>>", "onEach1:${Thread.currentThread().name}")
        }.onEmpty {
            Log.i("===>>>", "onEmpty:${Thread.currentThread().name}")
        }.catch { exception ->
            Log.i("===>>>", "onCatch:${Thread.currentThread().name} exception:${exception}")
        }.flowOn(Dispatchers.Main).launchIn(this)
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
                "Base模块" -> {
                    startActivity(Intent(this, MainBaseActivity::class.java))
                }
            }
        }
    }
}