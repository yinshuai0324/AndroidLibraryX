package com.ooimi.base.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ooimi.base.BaseLibrary
import com.ooimi.base.R
import com.ooimi.base.pagestatus.MultiStateContainer
import com.ooimi.base.pagestatus.PageStatus
import com.ooimi.base.pagestatus.bindMultiState
import com.ooimi.base.data.ViewModelEventType
import com.ooimi.base.dialog.LoadingModelDialog
import com.ooimi.base.expand.getVmClazz
import com.ooimi.base.utils.inflateBindingWithGeneric
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import com.ooimi.base.viewmodel.BaseViewModel
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import org.greenrobot.eventbus.EventBus

/**
 * @类作用描述:Activity基类
 * @作者: 尹帅
 * @创建时间: 2022-09-05 15:36
 */
abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity(),
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

    /**
     * 加载框
     */
    var loadingView: LoadingModelDialog? = null

    /**
     * 监听ActivityResult
     */
    var onActivityResult: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        if (onIsLockVerticalScreen()) {
            //锁定为竖屏
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
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
        if (isEnableEventBus()) {
            EventBus.getDefault().register(this)
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
                        toast(it.desc)
                    }
                    ViewModelEventType.EVENT_DIALOG -> {
                        //TODO 显示弹窗
                    }
                    ViewModelEventType.EVENT_SHOW_LOADING_DIALOG -> {
                        // 显示加载弹窗
                        showLoading(it.desc)
                    }
                    ViewModelEventType.EVENT_CHANGE_PAGE_STATUS -> {
                        pageStatus.changePageStatus(it.pageStatus)
                    }
                    ViewModelEventType.EVENT_DISMISS_LOADING_DIALOG -> {
                        // 关闭加载弹窗
                        dismissLoading()
                    }
                    ViewModelEventType.EVENT_FINISH_PAGE -> {
                        finish()
                    }
                    ViewModelEventType.EVENT_REFRESH_DATA -> {
                        //刷新页面数据
                        onRefreshPageData()
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
    fun setStatusBarColor(color: Int, iconDark: Boolean, isIntrusion: Boolean = false) {
        statusBar {
            fitWindow = !isIntrusion
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

    /**
     * 透明状态栏
     */
    fun transparentBar(
        statusBar: Boolean = false,
        navigationBar: Boolean = false,
        statusDark: Boolean = false,
        navDark: Boolean = false
    ) {
        if (statusBar) {
            UltimateBarX.statusBar(this).light(statusDark).transparent().apply()
        }
        if (navigationBar) {
            UltimateBarX.navigationBar(this).light(navDark).transparent().apply()
        }
    }

    /**
     * 是否启用EventBus
     */
    open fun isEnableEventBus(): Boolean {
        return false
    }

    /**
     * 刷新页面数据
     */
    open fun onRefreshPageData() {

    }

    /**
     * 是否锁定竖屏
     */
    open fun onIsLockVerticalScreen(): Boolean {
        return true
    }


    fun toast(msg: String?) {
        msg?.let {
            if (BaseLibrary.config?.toastModelImp != null) {
                BaseLibrary.config?.toastModelImp?.toast(this, it)
            } else {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }


    /**
     * 显示加载框
     */
    fun showLoading(msg: String? = "正在加载...") {
        if (loadingView == null) {
            loadingView = LoadingModelDialog(this)
        }
        loadingView?.setMessage(msg)
        if (loadingView?.isShow() == true) {
            loadingView?.dismiss()
        }
        loadingView?.show()
    }

    /**
     * 关闭加载框
     */
    fun dismissLoading() {
        loadingView?.let {
            if (it.isShow()) {
                it.dismiss()
            }
        }
    }


    /**
     * View点击
     */
    open fun onViewClick(view: View) {

    }

    /**
     * 添加ViewId点击事件
     */
    fun addViewClicks(vararg ids: Int) {
        try {
            ids.forEach {
                viewBinding.root.findViewById<View>(it)?.setOnClickListener {
                    onViewClick(it)
                }
            }
        } catch (e: Exception) {
            throw Exception("设置点击事件出错啦 error:$e")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isEnableEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        onActivityResult?.invoke(requestCode, resultCode, data)
    }

}