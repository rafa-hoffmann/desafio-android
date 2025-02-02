package com.desafio.picpay.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NetworkUser(
    @SerialName("img") val img: String,
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String
)