package com.project.android.app.inotes.di.module.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.android.app.inotes.di.scope.ViewModelKey
import com.project.android.app.inotes.ui.login.LoginViewModel
import com.project.android.app.inotes.ui.register.RegisterViewModel
import com.project.android.app.inotes.ui.verification.VerificationViewModel
import com.project.android.app.inotes.util.ViewModelFactoryUtil
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {

    @Binds
    internal abstract fun bindViewModelFactory(viewModelFactoryUtil: ViewModelFactoryUtil): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel::class)
    internal abstract fun bindRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(VerificationViewModel::class)
    internal abstract fun bindVerificationViewModel(verificationViewModel: VerificationViewModel): ViewModel

}