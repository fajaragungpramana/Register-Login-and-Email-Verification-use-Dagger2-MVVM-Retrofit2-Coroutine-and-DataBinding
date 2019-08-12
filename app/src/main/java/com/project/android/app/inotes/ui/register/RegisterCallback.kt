package com.project.android.app.inotes.ui.register

interface RegisterCallback {

    fun onSuccess()
    fun onFailure(responseMessage: String, responseCode: Int)

}