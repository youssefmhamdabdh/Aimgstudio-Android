package com.aimgstudio.data.util

import android.content.Context
import com.aimgstudio.data.local.CollectionEntity
import com.aimgstudio.data.local.ScreenshotEntity
import com.aimgstudio.data.models.Collection
import com.aimgstudio.data.models.Screenshot
import kotlinx.serialization.json.Json
import java.io.File

object JsonHelper {
    private val json = Json { 
        ignoreUnknownKeys = true 
        coerceInputValues = true
        prettyPrint = true
    }

    fun importBackup(context: Context, filePath: String): Pair<List<Screenshot>, List<Collection>> {
        val file = File(filePath)
        val content = file.readText()
        
        // The backup file contains a root object with a 'backup' field
        val root = json.decodeFromString<BackupRoot>(content)
        return Pair(root.backup.screenshots, root.backup.collections)
    }

    fun exportBackup(screenshots: List<Screenshot>, collections: List<Collection>): String {
        val root = BackupRoot(
            backup = BackupData(
                screenshots = screenshots,
                collections = collections,
                createdAt = java.time.Instant.now().toString()
            )
        )
        return json.encodeToString(BackupRoot.serializer(), root)
    }

    @kotlinx.serialization.Serializable
    private data class BackupRoot(val backup: BackupData)

    @kotlinx.serialization.Serializable
    private data class BackupData(
        val screenshots: List<Screenshot>,
        val collections: List<Collection>,
        val createdAt: String
    )
}
