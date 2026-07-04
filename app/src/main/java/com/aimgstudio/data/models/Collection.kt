package com.aimgstudio.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Collection(
    val id: String,
    val name: String,
    val screenshotIds: List<String>,
    val lastModified: String
)
