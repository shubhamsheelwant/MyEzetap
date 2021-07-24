package com.example.myezetap.Model

import com.google.gson.annotations.SerializedName


data class CustomUiResponse(
    @SerializedName("logo-url") val logoUrl: String,
    @SerializedName("heading-text") val headingText: String,
    @SerializedName("uidata") val uidata: List<UiDataItem>

)

data class UiDataItem(
    @SerializedName("uitype") val uitype: String,
    @SerializedName("value") val value: String,
    @SerializedName("key") val key: String,
    @SerializedName("hint") val hint: String)