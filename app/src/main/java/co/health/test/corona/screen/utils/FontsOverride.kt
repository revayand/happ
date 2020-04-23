package co.health.test.corona.screen.utils

import android.content.Context
import android.graphics.Typeface

import java.lang.reflect.Field

object FontsOverride {

    fun setYekan(context: Context) {
        setFont(context, "DEFAULT")
        setFont(context, "MONOSPACE")
        setFont(context, "SERIF")
        setFont(context, "SANS_SERIF")

    }


    private fun setFont(context: Context, staticTypefaceFieldName: String) {
        val regular = Typeface.createFromAsset(
            context.assets,
            "fonts/IRANSansMobile.ttf"
        )
        replaceFont(staticTypefaceFieldName, regular)
    }

    private fun replaceFont(
        staticTypefaceFieldName: String,
        newTypeface: Typeface
    ) {
        try {
            val staticField = Typeface::class.java
                .getDeclaredField(staticTypefaceFieldName)
            staticField.isAccessible = true
            staticField.set(null, newTypeface)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}