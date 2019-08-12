package com.project.android.app.inotes.ui.adapter

import android.app.Activity
import android.view.View
import android.view.WindowManager
import androidx.databinding.BindingAdapter
import com.project.android.app.inotes.extension.gone
import com.project.android.app.inotes.extension.invisible
import com.project.android.app.inotes.extension.visible

object BindingAdapter {

    @JvmStatic
    @BindingAdapter("onProgressBar")
    fun onProgressBar(view: View, onProgressBar: Boolean?) {
        if (onProgressBar.let { it != null && it == true }) {
            view.visible()

            val activity = view.context as Activity
            activity.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        } else {
            view.gone()

            val activity = view.context as Activity
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

        }
    }

    @JvmStatic
    @BindingAdapter("isErrorField")
    fun isErrorField(view: View, isErrorField: Boolean?) {
        if (isErrorField.let { it != null && it == true })  view.visible() else view.invisible()
    }

}