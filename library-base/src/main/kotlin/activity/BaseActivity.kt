package activity

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import pagestatus.MultiStateContainer
import pagestatus.PageStatus
import pagestatus.bindMultiState
import data.ViewModelEventType
import expand.getVmClazz
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import utils.inflateBindingWithGeneric
import viewmodel.BaseViewModel

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
}