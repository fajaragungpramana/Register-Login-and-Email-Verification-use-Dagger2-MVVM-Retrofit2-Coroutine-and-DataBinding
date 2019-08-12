package com.project.android.app.inotes.ui.register

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.project.android.app.inotes.R
import com.project.android.app.inotes.data.model.Entity
import com.project.android.app.inotes.databinding.ActivityRegisterBinding
import com.project.android.app.inotes.ui.base.BaseActivity
import com.project.android.app.inotes.ui.login.LoginActivity
import com.project.android.app.inotes.ui.starter.StarterActivity
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.startActivity

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(), RegisterCallback {

    private val viewModel by lazy {
        ViewModelProviders.of(this@RegisterActivity, viewModelFactory)[RegisterViewModel::class.java]
            .apply { init(this@RegisterActivity) }
    }

    override fun getContentView(): Int = R.layout.activity_register

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        getBinding()?.let {
            it.viewModel = viewModel
            it.user = Entity.User()
        }

        iv_back.setOnClickListener {
            startActivity<StarterActivity>()
            finish()
        }
        tv_to_login.setOnClickListener { toLoginPage() }
    }

    override fun onSuccess() {
        onToastMessage(getString(R.string.registration_success))
        toLoginPage()
    }

    override fun onFailure(responseMessage: String, responseCode: Int) {
        if (responseCode == 406)
            onToastMessage(getString(R.string.error_registration_account_with_same_email_already_exist))
        else
            onToastMessage(responseMessage)
    }

    private fun toLoginPage() {
        startActivity<LoginActivity>()
        finish()
    }

}