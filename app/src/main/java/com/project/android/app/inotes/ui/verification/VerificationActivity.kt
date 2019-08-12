package com.project.android.app.inotes.ui.verification

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.project.android.app.inotes.R
import com.project.android.app.inotes.data.model.constant.Constant
import com.project.android.app.inotes.databinding.ActivityVerificationBinding
import com.project.android.app.inotes.ui.base.BaseActivity
import com.project.android.app.inotes.ui.login.LoginActivity
import org.jetbrains.anko.startActivity

class VerificationActivity : BaseActivity<ActivityVerificationBinding>(), VerificationCallback {

    private val viewModel by lazy {
        ViewModelProviders.of(this@VerificationActivity, viewModelFactory)[VerificationViewModel::class.java]
            .apply { init(this@VerificationActivity) }
    }

    override fun getContentView(): Int = R.layout.activity_verification

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        getBinding()?.viewModel = viewModel

        viewModel.getUserLogin(intent.getStringExtra(Constant.IntentKey.EMAIL))
    }

    override fun onBackPressed() { toLoginPage() }

    override fun onSuccess() {
        onToastMessage(getString(R.string.success_email_verification_was_sent))

        toLoginPage()
    }

    private fun toLoginPage() {
        startActivity<LoginActivity>()
        finish()
    }

}