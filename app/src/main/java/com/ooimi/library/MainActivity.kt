package com.ooimi.library

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ooimi.library.adapter.HomeAdapter
import com.ooimi.library.ui.WidgetActivity
import com.ooimi.library.ui.widget.ButtonActivity
import com.ooimi.library.ui.widget.ImageActivity
import com.ooimi.library.ui.widget.LayoutActivity

class MainActivity : AppCompatActivity() {
    private var adapter: HomeAdapter? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)
        initRecyclerView()
        adapter?.addData("控件模块")
    }


    private fun initRecyclerView() {
        adapter = HomeAdapter()
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter

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