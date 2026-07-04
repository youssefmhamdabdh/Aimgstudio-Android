package com.aimgstudio.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Screenshot(
    val id: String,
    val path: String,
    val title: String,
    val description: String,
    val tags: List<String>,
    val collectionIds: List<String>,
    val aiProcessed: Boolean,
    val addedOn: String,
    val fileSize: Long,
    val isDeleted: Boolean = false
)
