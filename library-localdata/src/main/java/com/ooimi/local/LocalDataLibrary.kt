package com.ooimi.local

import android.content.Context
import com.ooimi.local.imp.LocalData

internal object LocalDataLibrary {
    internal var config: LocalDataLibraryBuilder? = null

    internal fun init(context: Context, builder: LocalDataLibraryBuilder) {
        config = builder
        LocalData.init(context)
    }
}