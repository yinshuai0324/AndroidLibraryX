package com.ooimi.database

import android.content.Context
import com.tencent.mmkv.MMKV
import java.lang.ref.WeakReference

/**
 * @类作用描述:数据持久化模块
 * @作者: 尹帅
 * @创建时间: 2022-10-19 10:39
 */
object DatabaseLibrary {
    internal const val LOG_TAG = "DatabaseLibrary"
    private var config: WeakReference<DatabaseLibraryBuilder>? = null

    internal var mmkv: MMKV? = null

    fun init(context: Context, config: DatabaseLibraryBuilder) {
        this.config = WeakReference<DatabaseLibraryBuilder>(config)
        MMKV.initialize(context)
        mmkv = MMKV.mmkvWithID(context.packageName, MMKV.MULTI_PROCESS_MODE)
    }

    internal fun getConfig(): DatabaseLibraryBuilder? {
        return config?.get()
    }
}