package com.ooimi.library
import android.util.Log
import com.ooimi.base.viewmodel.BaseViewModel
import com.ooimi.library.bean.AppThemeBean
import com.ooimi.library.network.api.MainApiService
import com.ooimi.network.expand.*

/**
 * @类作用描述:
 * @作者: 尹帅
 * @创建时间: 2022-09-06 15:45
 */
class MainActivityViewModel : BaseViewModel() {
    private val apiService = getDefaultApiService(MainApiService::class.java)

    fun getOrderInfo() {
        safeApiRequest<AppThemeBean> {
            api = { apiService.test() }
            onSuccess {
                Log.i("===>>>", "后端返回的数据:${it.t1Text}")
            }
            onFailed { errorMsg, code ->
                Log.i("===>>>", "请求失败:${errorMsg}")
            }
        }
    }
}