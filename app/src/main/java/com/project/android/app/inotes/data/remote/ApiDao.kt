package com.project.android.app.inotes.data.remote

import com.project.android.app.inotes.data.model.Entity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiDao {

    interface INotes {

        @FormUrlEncoded
        @POST("auth/register")
        fun userRegisterAsync(@Header("key") key: String?, @FieldMap map: MutableMap<String, String?>) : Deferred<Response<Entity.User>>

        @FormUrlEncoded
        @POST("auth/login")
        fun userLoginAsync(@Header("key") key: String?, @FieldMap map: MutableMap<String, String?>): Deferred<Response<Entity.User>>

        @GET("auth/requestemailverification")
        fun requestEmailVerificationAsync(@Header("key") key: String?, @Query("email") email: String?): Deferred<Response<Entity.User>>

    }

}