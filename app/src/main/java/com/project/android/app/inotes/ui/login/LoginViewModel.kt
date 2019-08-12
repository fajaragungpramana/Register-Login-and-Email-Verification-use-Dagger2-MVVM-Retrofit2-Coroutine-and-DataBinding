package com.project.android.app.inotes.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.android.app.inotes.data.model.Entity
import com.project.android.app.inotes.data.remote.ApiResponse
import com.project.android.app.inotes.data.repository.INotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val iNotesRepository: INotesRepository) : ViewModel() {

    private lateinit var loginCallback: LoginCallback
    fun init(loginCallback: LoginCallback) {
        this.loginCallback = loginCallback
    }

    private val _onProgressBar = MutableLiveData<Boolean>()
    fun onProgressBar() = _onProgressBar as LiveData<Boolean>

    private val _isErrorEmail = MutableLiveData<Boolean>()
    fun isErrorEmail() = _isErrorEmail as LiveData<Boolean>

    private val _isErrorPassword = MutableLiveData<Boolean>()
    fun isErrorPassword() = _isErrorPassword as LiveData<Boolean>

    fun watchFieldEmail(text: CharSequence?) { _isErrorEmail.value = !Patterns.EMAIL_ADDRESS.matcher(text).matches() }
    fun watchFieldPassword(text: CharSequence?) { _isErrorPassword.value = text.let { it != null && it.isEmpty() } }

    fun doUserLogging(user: Entity.User) {

        if (contentFillValidate(user)) {

            val map = mutableMapOf(
                "email" to user.email,
                "password" to user.password
            )

            _onProgressBar.value = true
            CoroutineScope(Dispatchers.IO).launch {

                when (val response = iNotesRepository.userLogin(map)) {

                    is ApiResponse.Success -> {
                        _onProgressBar.postValue(false)

                        loginCallback.onSuccess()
                    }

                    is ApiResponse.Failure -> {
                        _onProgressBar.postValue(false)

                        loginCallback.onFailure(response.message, response.code)
                    }

                }

            }

        }

    }

    private fun contentFillValidate(user: Entity.User): Boolean {
        if (user.email.let { it != null && Patterns.EMAIL_ADDRESS.matcher(it).matches() })
            if (user.password.let { it != null && it.isNotEmpty() })
                return true
            else
                _isErrorPassword.value = true
        else
            _isErrorEmail.value = true

        return false
    }

}