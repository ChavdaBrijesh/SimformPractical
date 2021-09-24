package com.brijesh.simform_practical.model

import com.brijesh.simform_practical.model.Info
import com.brijesh.simform_practical.model.UserData
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RandomUserResponse(@SerializedName("results") @Expose var userData: List<UserData>? = null,
                              @SerializedName("info") @Expose var info: Info
)

