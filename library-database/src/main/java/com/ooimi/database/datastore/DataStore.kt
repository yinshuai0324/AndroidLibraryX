package com.ooimi.database.datastore

import android.text.TextUtils
import android.util.Log
import com.ooimi.database.DatabaseLibrary
import com.ooimi.database.DatabaseLibrary.LOG_TAG

object DataStore {
    private val mmkv = DatabaseLibrary.mmkv
    fun put(key: String?, value: Any?) {
        if (TextUtils.isEmpty(key) || value == null) {
            Log.e(LOG_TAG, "put data failure，key value is empty (key:$key value:$value)")
            return
        }
    }
}