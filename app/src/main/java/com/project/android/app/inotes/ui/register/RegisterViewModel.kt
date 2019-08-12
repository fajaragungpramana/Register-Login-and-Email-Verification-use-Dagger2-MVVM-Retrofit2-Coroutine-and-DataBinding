package com.project.android.app.inotes.ui.register

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

class RegisterViewModel @Inject constructor(private val iNotesRepository: INotesRepository) : ViewModel() {

    private lateinit var registerCallback: RegisterCallback
    fun init(registerCallback: RegisterCallback) {
        this.registerCallback = registerCallback
    }

    private val _onProgressBar = MutableLiveData<Boolean>()
    fun onProgressBar() = _onProgressBar as LiveData<Boolean>

    private val _isErrorFirstName = MutableLiveData<Boolean>()
    fun isErrorFirstName() = _isErrorFirstName as LiveData<Boolean>

    private val _isErrorLastName = MutableLiveData<Boolean>()
    fun isErrorLastName() = _isErrorLastName as LiveData<Boolean>

    private val _isErrorEmail = MutableLiveData<Boolean>()
    fun isErrorEmail() = _isErrorEmail as LiveData<Boolean>

    private val _isErrorPassword = MutableLiveData<Boolean>()
    fun isErrorPassword() = _isErrorPassword as LiveData<Boolean>

    fun watchFieldFirstName(text: CharSequence?) { _isErrorFirstName.value = text.let { it != null && it.length < 4 } }
    fun watchFieldLastName(text: CharSequence?) { _isErrorLastName.value = text.let { it != null && it.length < 4 } }
    fun watchFieldEmail(text: CharSequence?) { _isErrorEmail.value = !Patterns.EMAIL_ADDRESS.matcher(text).matches() }
    fun watchFieldPassword(text: CharSequence?) { _isErrorPassword.value = text.let { it != null && it.length < 8 } }

    fun doUserRegistration(user: Entity.User) {

        if (contentFillValidate(user)) {

            val map = mutableMapOf(
                "firstName" to user.firstName,
                "lastName" to user.lastName,
                "email" to user.email,
                "password" to user.password
            )

            _onProgressBar.value = true
            CoroutineScope(Dispatchers.IO).launch {

                when (val response = iNotesRepository.userRegister(map)) {

                    is ApiResponse.Success -> {
                        _onProgressBar.postValue(false)

                        registerCallback.onSuccess()
                    }

                    is ApiResponse.Failure -> {
                        _onProgressBar.postValue(false)

                        registerCallback.onFailure(response.message, response.code)
                    }

                }

            }

        }

    }

    private fun contentFillValidate(user: Entity.User): Boolean {
        if (user.firstName.let { it != null && it.length >= 4 })
            if (user.lastName.let { it != null && it.length >= 4 })
                if (user.email.let { it != null && Patterns.EMAIL_ADDRESS.matcher(it).matches() })
                    if (user.password.let { it != null && it.length >= 8 })
                        return true
                    else
                        _isErrorPassword.value = true
                else
                    _isErrorEmail.value = true
            else
                _isErrorLastName.value = true
        else
            _isErrorFirstName.value = true

        return false
    }

}