package com.project.android.app.inotes.di.module.builder

import com.project.android.app.inotes.ui.login.LoginActivity
import com.project.android.app.inotes.ui.register.RegisterActivity
import com.project.android.app.inotes.ui.verification.VerificationActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        ViewModelBuilder::class
    ]
)
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun contributeRegisterActivity(): RegisterActivity

    @ContributesAndroidInjector
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun contributeVerificationActivity(): VerificationActivity

}