package com.aimgstudio.domain.repository

import com.aimgstudio.data.local.CollectionDao
import com.aimgstudio.data.local.CollectionEntity
import com.aimgstudio.data.models.Collection

class CollectionRepository(private val collectionDao: CollectionDao) {
    suspend fun getAllCollections(): List<Collection> {
        return collectionDao.getAllCollections().map { entity ->
            Collection(
                id = entity.id,
                name = entity.name,
                screenshotIds = entity.screenshotIds.split(",").filter { it.isNotBlank() },
                lastModified = entity.lastModified
            )
        }
    }

    suspend fun insertCollections(collections: List<Collection>) {
        val entities = collections.map {
            CollectionEntity(
                id = it.id,
                name = it.name,
                screenshotIds = it.screenshotIds.joinToString(","),
                lastModified = it.lastModified
            )
        }
        collectionDao.insertAll(entities)
    }
}
