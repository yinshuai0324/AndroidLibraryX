package com.ooimi.database

import android.content.Context


/**
 * @类作用描述:数据持久化参数配置
 * @作者: 尹帅
 * @创建时间: 2022-10-13 19:33
 */
class DatabaseLibraryBuilder constructor(private val context: Context) {
    var preferenceName: String? = "PreferencesDataStore"

    fun setPreferenceDataStoreName(name: String) {
        this.preferenceName = name
    }

    fun init() {
        DatabaseLibrary.init(context, this)
    }
}