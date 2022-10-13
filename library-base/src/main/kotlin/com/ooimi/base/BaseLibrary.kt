package com.ooimi.base

internal object BaseLibrary {
    internal var config: BaseLibraryBuilder? = null
    
    internal fun init(builder: BaseLibraryBuilder) {
        config = builder
    }
}