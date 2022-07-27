package com.ooimi.library.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ooimi.library.R
import com.ooimi.library.adapter.HomeAdapter
import com.ooimi.library.ui.widget.ButtonActivity
import com.ooimi.library.ui.widget.ImageActivity
import com.ooimi.library.ui.widget.LayoutActivity

class WidgetActivity : AppCompatActivity() {
    private var adapter: HomeAdapter? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget)
        title = "控件模块"
        recyclerView = findViewById(R.id.recyclerView)
        initRecyclerView()
        adapter?.addData("图片控件")
        adapter?.addData("按钮控件")
        adapter?.addData("容器控件")
    }

    private fun initRecyclerView() {
        adapter = HomeAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter

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