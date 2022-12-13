package com.ooimi.library.theme

import android.content.Context
import com.dolatkia.animatedThemeManager.AppTheme

interface BaseAppTheme : AppTheme {
    fun firstActivityBackgroundColor(context: Context): Int
    fun firstActivityTextColor(context: Context): Int
    fun firstActivityIconColor(context: Context): Int
}