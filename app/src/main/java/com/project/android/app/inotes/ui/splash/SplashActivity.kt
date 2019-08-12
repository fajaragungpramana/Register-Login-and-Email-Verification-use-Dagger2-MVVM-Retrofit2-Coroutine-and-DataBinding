package com.project.android.app.inotes.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.project.android.app.inotes.R
import com.project.android.app.inotes.data.model.constant.Constant
import com.project.android.app.inotes.ui.starter.StarterActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({

            startActivity<StarterActivity>()
            finish()

        }, Constant.Var.SPLASH_TIME)

    }

}