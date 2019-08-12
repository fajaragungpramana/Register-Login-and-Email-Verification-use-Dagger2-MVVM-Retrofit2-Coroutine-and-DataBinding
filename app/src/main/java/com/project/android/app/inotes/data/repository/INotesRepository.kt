package com.project.android.app.inotes.data.repository

import com.project.android.app.inotes.data.model.Entity
import com.project.android.app.inotes.data.model.constant.Constant
import com.project.android.app.inotes.data.remote.ApiDao
import com.project.android.app.inotes.data.remote.ApiResponse

class INotesRepository(private val iNotesDao: ApiDao.INotes) {

    suspend fun userRegister(map: MutableMap<String, String?>) : ApiResponse<Entity.User?> {
        val response = iNotesDao.userRegisterAsync(Constant.ApiKey.INOTES, map).await()

        return if (response.isSuccessful)
            ApiResponse.Success(response.body())
        else
            ApiResponse.Failure(response.message(), response.code())
    }

    suspend fun userLogin(map: MutableMap<String, String?>) : ApiResponse<Entity.User?> {
        val response = iNotesDao.userLoginAsync(Constant.ApiKey.INOTES, map).await()

        return if (response.isSuccessful)
            ApiResponse.Success(response.body())
        else
            ApiResponse.Failure(response.message(), response.code())
    }

    suspend fun requestEmailVerification(email: String?) : ApiResponse<Entity.User?> {
        val response = iNotesDao.requestEmailVerificationAsync(Constant.ApiKey.INOTES, email).await()

        return if (response.isSuccessful)
            ApiResponse.Success(response.body())
        else
            ApiResponse.Failure(response.message(), response.code())
    }

}