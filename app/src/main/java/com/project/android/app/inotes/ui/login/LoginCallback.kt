package com.project.android.app.inotes.ui.login

interface LoginCallback {

    fun onSuccess()
    fun onFailure(responseMessage: String?, responseCode: Int)

}