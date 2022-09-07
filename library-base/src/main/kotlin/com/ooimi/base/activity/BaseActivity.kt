package com.ooimi.base.activity

import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ooimi.base.R
import com.ooimi.base.pagestatus.MultiStateContainer
import com.ooimi.base.pagestatus.PageStatus
import com.ooimi.base.pagestatus.bindMultiState
import com.ooimi.base.data.ViewModelEventType
import com.ooimi.base.expand.getVmClazz
import com.ooimi.base.utils.inflateBindingWithGeneric
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import com.ooimi.base.viewmodel.BaseViewModel
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar

/**
 * @类作用描述:Activity基类
 * @作者: 尹帅
 * @创建时间: 2022-09-05 15:36
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : FragmentActivity(),
    CoroutineScope by MainScope() {

    /**
     * ViewModel
     */
    lateinit var viewModel: VM

    /**
     * ViewBinding
     */
    lateinit var viewBinding: VB

    /**
     * 页面状态显示View
     */
    lateinit var pageStatus: MultiStateContainer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //获取当前ViewBinding
        viewBinding = inflateBindingWithGeneric(layoutInflater)
        //设置ContentView
        setContentView(viewBinding.root)
        if (isSetDefaultStatusConfig()) {
            defaultStatusBarAndNavigationBar()
        }
        pageStatus = bindMultiState {
            //重试
            onRetryClick()
        }
        if (defaultLoadingStatus()) {
            pageStatus.changePageStatus(PageStatus.STATUS_LOADING)
        }
        viewModel = createViewModel()
        handlerViewModelNotice()
        createdObserve()
        initData()
    }

    private fun handlerViewModelNotice() {
        viewModel.eventNoticeData.observe(this) {
            it?.let {
                when (it.type) {
                    ViewModelEventType.EVENT_TOAST -> {
                        Toast.makeText(this, it.desc, Toast.LENGTH_SHORT).show()
                    }
                    ViewModelEventType.EVENT_DIALOG -> {
                        //TODO 显示弹窗
                    }
                    ViewModelEventType.EVENT_SHOW_LOADING_DIALOG -> {
                        //TODO 显示加载弹窗
                    }
                    ViewModelEventType.EVENT_CHANGE_PAGE_STATUS -> {
                        pageStatus.changePageStatus(it.pageStatus)
                    }
                    ViewModelEventType.EVENT_DISMISS_LOADING_DIALOG -> {
                        //TODO 关闭加载弹窗
                    }
                    ViewModelEventType.EVENT_FINISH_PAGE -> {
                        finish()
                    }
                    ViewModelEventType.EVENT_NONE -> {
                        //没有操作
                    }
                }
            }
        }
    }

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 创建订阅
     */
    abstract fun createdObserve()


    /**
     * 重试按钮点击
     */
    open fun onRetryClick() {

    }

    /**
     * 默认是否是加载状态
     */
    open fun defaultLoadingStatus(): Boolean {
        return false
    }


    /**
     * 改变页面状态
     */
    fun changePageStatus(status: PageStatus) {
        pageStatus.changePageStatus(status)
    }


    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(getVmClazz(this))
    }

    /**
     * 显示Toast
     */
    fun showToast(msg: String?) {
        msg?.let { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() }
    }

    /**
     * 是否设置默认的状态栏和导航栏样式
     */
    open fun isSetDefaultStatusConfig(): Boolean {
        return true
    }


    /**
     * 默认的状态栏和导航栏
     */
    private fun defaultStatusBarAndNavigationBar() {
        setStatusBarColor(R.color.white, true)
        setNavigationBar(R.color.white, true)
    }

    /**
     * 设置状态栏颜色
     * @iconDark 状态栏icon颜色 false:白色 true:灰色
     */
    fun setStatusBarColor(color: Int, iconDark: Boolean) {
        statusBar {
            colorRes = color
            light = iconDark
        }
    }

    /**
     * 设置导航栏颜色
     * @iconDark 状态栏icon颜色 false:白色 true:灰色
     */
    fun setNavigationBar(color: Int, iconDark: Boolean) {
        navigationBar {
            colorRes = color
            light = iconDark
        }
    }
}