package com.brijesh.simform_practical.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Info(@SerializedName("seed") @Expose var seed: String,
                              @SerializedName("results") @Expose var results: Integer,
                @SerializedName("page") @Expose var page: Integer,
                @SerializedName("version") @Expose var version: String)