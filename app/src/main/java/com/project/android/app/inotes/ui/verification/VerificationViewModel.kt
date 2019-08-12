package com.project.android.app.inotes.ui.verification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.android.app.inotes.data.remote.ApiResponse
import com.project.android.app.inotes.data.repository.INotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class VerificationViewModel @Inject constructor(private val iNotesRepository: INotesRepository) : ViewModel() {

    private lateinit var verificationCallback: VerificationCallback
    fun init(verificationCallback: VerificationCallback) {
        this.verificationCallback = verificationCallback
    }

    private lateinit var userLogin: String
    fun getUserLogin(email: String) { this.userLogin = email }

    private val _onProgressBar = MutableLiveData<Boolean>()
    fun onProgressBar() = _onProgressBar as LiveData<Boolean>

    fun doRequestEmailVerification() {

        _onProgressBar.value = true
        CoroutineScope(Dispatchers.IO).launch {

            when (val response = iNotesRepository.requestEmailVerification(userLogin)) {

                is ApiResponse.Success -> {
                    _onProgressBar.postValue(false)

                    verificationCallback.onSuccess()
                }

                is ApiResponse.Failure -> {
                    _onProgressBar.postValue(false)

                    verificationCallback.onToastMessage(response.message)
                }

            }

        }

    }

}