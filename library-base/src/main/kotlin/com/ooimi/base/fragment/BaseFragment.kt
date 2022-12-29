package com.ooimi.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.ooimi.base.BaseLibrary
import com.ooimi.base.pagestatus.MultiStateContainer
import com.ooimi.base.pagestatus.MultiStatePage
import com.ooimi.base.pagestatus.PageStatus
import com.ooimi.base.data.ViewModelEventType
import com.ooimi.base.dialog.LoadingModelDialog
import com.ooimi.base.expand.getVmClazz
import com.ooimi.base.utils.inflateBindingWithGeneric
import com.ooimi.base.viewmodel.BaseViewModel
import com.zackratos.ultimatebarx.ultimatebarx.java.UltimateBarX
import com.zackratos.ultimatebarx.ultimatebarx.navigationBar
import com.zackratos.ultimatebarx.ultimatebarx.statusBar
import org.greenrobot.eventbus.EventBus

/**
 * 创建者：yinshuai
 * 创建时间：2021/7/1 09:02
 * 作用描述：Fragment 基类  MVVM架构 使用ViewBind查找控件
 * Androidx 下的Fragment 新增了setMaxLifecycle 所以一般情况下以及使用ViewPager2 能够正确的回调 onResume
 * 对于使用add+show+hide的方式 请使用ActivityExpand.kt 里面的loadFragmentsTransaction 和 showHideFragmentTransaction
 */
abstract class BaseFragment<VM : BaseViewModel, VB : ViewBinding> : Fragment() {
    /**
     * 是否已加载数据
     */
    private var isLoadData = false

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
    private lateinit var pageStatus: MultiStateContainer


    private val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"


    /**
     * 加载框
     */
    var loadingView: LoadingModelDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //解决Fragment重叠的问题
        if (savedInstanceState != null) {
            val isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN)
            val ft = parentFragmentManager.beginTransaction()
            if (isSupportHidden) {
                ft.show(this)
            } else {
                ft.hide(this)
            }
            ft.commit()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        //解决Fragment重叠的问题
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (isEnableEventBus()) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflateBindingWithGeneric(layoutInflater, container, false)
        pageStatus = MultiStatePage.bindMultiState(viewBinding.root) {
            //重试处理
            onRetry()
        }
        if (defaultLoadingStatus()) {
            pageStatus.changePageStatus(PageStatus.STATUS_LOADING)
        }
        viewModel = createViewModel()
        handlerViewModelNotice()
        createdObserve()
        return pageStatus
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
    }


    override fun onResume() {
        super.onResume()
        if (!isLoadData && !isHidden) {
            lazyInit()
            isLoadData = true
        } else {
            onVisible()
        }
    }

    override fun onPause() {
        super.onPause()
        onInvisible()
    }

    /**
     * 懒加载数据
     */
    abstract fun lazyInit()

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 创建订阅
     */
    abstract fun createdObserve()

    /**
     * 界面可见时回调
     */
    open fun onVisible() {

    }

    /**
     * 界面不可见时回调
     */
    open fun onInvisible() {

    }

    /**
     * 页面重试回调
     */
    open fun onRetry() {

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
     * 获取协程作用范围
     */
    fun getLifecycleScope() = lifecycle

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(getVmClazz(this))
    }


    /**
     * 处理ViewModel的通知
     */
    private fun handlerViewModelNotice() {
        viewModel.eventNoticeData.observe(this) {
            it?.let {
                when (it.type) {
                    ViewModelEventType.EVENT_TOAST -> {
                        //显示Toast
                        if (isAdded) {
                            toast(it.desc)
                        }
                    }
                    ViewModelEventType.EVENT_DIALOG -> {
                        //TODO 显示弹窗
                    }
                    ViewModelEventType.EVENT_SHOW_LOADING_DIALOG -> {
                        // 显示加载弹窗
                        showLoading(it.desc)
                    }
                    ViewModelEventType.EVENT_DISMISS_LOADING_DIALOG -> {
                        // 显示消失弹窗
                        dismissLoading()
                    }
                    ViewModelEventType.EVENT_CHANGE_PAGE_STATUS -> {
                        //改变页面状态
                        changePageStatus(it.pageStatus)
                    }
                    ViewModelEventType.EVENT_REFRESH_DATA -> {
                        //刷新页面数据
                        onRefreshPageData()
                    }
                    ViewModelEventType.EVENT_RESET_REFRESH_STATUS -> {
                        //重置刷新状态
                        resetRefreshStatus()
                    }
                    else -> {
                        //无需处理
                    }
                }
            }
        }
    }

    /**
     * 显示Toast
     */
    fun toast(msg: String?) {
        msg?.let {
            if (BaseLibrary.config?.toastModelImp != null) {
                BaseLibrary.config?.toastModelImp?.toast(requireContext(), it)
            } else {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }
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

    /**
     * 透明状态栏
     */
    fun transparentBar(statusBar: Boolean = false, navigationBar: Boolean = false) {
        if (statusBar) {
            UltimateBarX.statusBar(this).transparent().apply()
        }
        if (navigationBar) {
            UltimateBarX.navigationBar(this).transparent().apply()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoadData = false
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
     * 重置刷新状态
     */
    open fun resetRefreshStatus() {

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


    /**
     * 显示加载框
     */
    fun showLoading(msg: String? = "正在加载...") {
        if (loadingView == null) {
            loadingView = LoadingModelDialog(requireContext())
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

    override fun onDetach() {
        super.onDetach()
        if (isEnableEventBus()) {
            EventBus.getDefault().unregister(this)
        }
    }
}