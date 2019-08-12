package com.project.android.app.inotes.ui.login

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.project.android.app.inotes.R
import com.project.android.app.inotes.data.model.Entity
import com.project.android.app.inotes.data.model.constant.Constant
import com.project.android.app.inotes.databinding.ActivityLoginBinding
import com.project.android.app.inotes.ui.base.BaseActivity
import com.project.android.app.inotes.ui.register.RegisterActivity
import com.project.android.app.inotes.ui.starter.StarterActivity
import com.project.android.app.inotes.ui.verification.VerificationActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(), LoginCallback {

    private val viewModel by lazy {
        ViewModelProviders.of(this@LoginActivity, viewModelFactory)[LoginViewModel::class.java]
            .apply { init(this@LoginActivity) }
    }

    override fun getContentView(): Int = R.layout.activity_login

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        getBinding()?.let {
            it.viewModel = viewModel
            it.user = Entity.User()
        }

        iv_back.setOnClickListener {
            startActivity<StarterActivity>()
            finish()
        }
        tv_to_register.setOnClickListener { startActivity<RegisterActivity>() }
    }

    override fun onSuccess() {

    }

    override fun onFailure(responseMessage: String?, responseCode: Int) {

        when (responseCode) {

            401 -> {
                startActivity<VerificationActivity>(Constant.IntentKey.EMAIL to getBinding()?.user?.email)
                finish()
            }

            406 -> onToastMessage(getString(R.string.error_login_wrong_password))

            400 -> onToastMessage(getString(R.string.error_login_email_is_not_registered))

            else -> onToastMessage(responseMessage)

        }

    }

}