package com.aimgstudio.domain.repository

import com.aimgstudio.data.local.*
import com.aimgstudio.data.models.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ScreenshotRepository(
    private val screenshotDao: ScreenshotDao,
    private val collectionDao: CollectionDao,
    private val logDao: LogDao
) {
    suspend fun getAllScreenshots(): List<Screenshot> {
        return screenshotDao.getAllScreenshots().map { entity ->
            Screenshot(
                id = entity.id,
                path = entity.path,
                title = entity.title,
                description = entity.description,
                tags = entity.tags.split(",").filter { it.isNotBlank() },
                collectionIds = entity.collectionIds.split(",").filter { it.isNotBlank() },
                aiProcessed = entity.aiProcessed,
                addedOn = entity.addedOn,
                fileSize = entity.fileSize,
                isDeleted = entity.isDeleted
            )
        }
    }

    suspend fun insertScreenshots(screenshots: List<Screenshot>) {
        val entities = screenshots.map {
            ScreenshotEntity(
                id = it.id,
                path = it.path,
                title = it.title,
                description = it.description,
                tags = it.tags.joinToString(","),
                collectionIds = it.collectionIds.joinToString(","),
                aiProcessed = it.aiProcessed,
                addedOn = it.addedOn,
                fileSize = it.fileSize,
                isDeleted = it.isDeleted
            )
        }
        screenshotDao.insertAll(entities)
    }

    suspend fun softDelete(id: String) {
        screenshotDao.softDelete(id)
    }

    suspend fun log(level: String, message: String) {
        logDao.insertLog(LogEntry(
            timestamp = System.currentTimeMillis(),
            level = level,
            message = message
        ))
    }

    suspend fun getLogs(): List<LogEntry> {
        return logDao.getAllLogs()
    }
}
