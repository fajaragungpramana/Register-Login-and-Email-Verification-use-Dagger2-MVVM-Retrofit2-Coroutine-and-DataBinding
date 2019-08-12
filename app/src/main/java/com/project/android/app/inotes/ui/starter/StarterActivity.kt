package com.project.android.app.inotes.ui.starter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.android.app.inotes.R
import com.project.android.app.inotes.ui.login.LoginActivity
import com.project.android.app.inotes.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_starter.*
import org.jetbrains.anko.startActivity

class StarterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_starter)

        btn_to_login.setOnClickListener { startActivity<LoginActivity>() }
        btn_to_register.setOnClickListener { startActivity<RegisterActivity>() }

    }

}