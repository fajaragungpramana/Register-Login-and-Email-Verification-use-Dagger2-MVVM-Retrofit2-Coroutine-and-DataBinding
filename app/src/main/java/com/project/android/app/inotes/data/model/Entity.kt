package com.project.android.app.inotes.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.google.gson.annotations.SerializedName

sealed class Entity : BaseObservable() {

    data class User(

        @SerializedName("photoUrl") var photoUrl: String? = null,
        @SerializedName("firstName") @Bindable var firstName: String? = null,
        @SerializedName("lastName") @Bindable var lastName: String? = null,
        @SerializedName("email") @Bindable var email: String? = null,
        @SerializedName("password") @Bindable var password: String? = null

    ) : Entity()

}