package com.aimgstudio.data.models

import kotlinx.serialization.Serializable

@Serializable
data class AppState(
    val apiKey: String = "",
    val model: String = "gemma-4-31b-it",
    val seedColor: String = "#6750A4"
)
