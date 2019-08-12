package com.project.android.app.inotes.ui.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * @param<B> - mean layout binding
 */
abstract class BaseActivity<B: ViewDataBinding> : DaggerAppCompatActivity(), BaseCallback {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    private var binding: B? = null

    protected abstract fun getContentView(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<B>(this@BaseActivity, getContentView())
            .apply { lifecycleOwner = this@BaseActivity }

        onActivityCreated(savedInstanceState)
    }

    protected abstract fun onActivityCreated(savedInstanceState: Bundle?)

    protected fun getBinding() = binding

    override fun onToastMessage(message: String?) {
        Handler(Looper.getMainLooper()).post {
            toast(message.toString())
        }
    }

}