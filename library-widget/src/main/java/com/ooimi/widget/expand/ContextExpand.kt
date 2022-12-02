package com.ooimi.widget.expand

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

/**
 * 资源转Bitmap
 */
fun Context.resources2Bitmap(res: Int): Bitmap? {
    return try {
        BitmapFactory.decodeResource(resources, res)
    } catch (e: Exception) {
        null
    }
}