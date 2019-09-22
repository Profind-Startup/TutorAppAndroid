package pe.com.retrofind.models

import com.google.gson.annotations.SerializedName



data class Subject(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("area") val area: String
)