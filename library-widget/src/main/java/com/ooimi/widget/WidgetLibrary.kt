package com.ooimi.widget

object WidgetLibrary {
    internal var config: WidgetLibraryBuilder? = null

    internal fun init(builder: WidgetLibraryBuilder) {
        config = builder
    }
}