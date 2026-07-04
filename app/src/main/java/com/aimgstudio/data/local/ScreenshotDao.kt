package com.aimgstudio.data.local

import androidx.room.*
import com.aimgstudio.data.models.Screenshot
import com.aimgstudio.data.models.Collection as CollectionModel

@Entity(tableName = "screenshots")
data class ScreenshotEntity(
    @PrimaryKey val id: String,
    val path: String,
    val title: String,
    val description: String,
    val tags: String, // Stored as comma-separated values
    val collectionIds: String, // Stored as comma-separated values
    val aiProcessed: Boolean,
    val addedOn: String,
    val fileSize: Long,
    val isDeleted: Boolean
)

@Entity(tableName = "collections")
data class CollectionEntity(
    @PrimaryKey val id: String,
    val name: String,
    val screenshotIds: String, // Stored as comma-separated values
    val lastModified: String
)

@Dao
interface ScreenshotDao {
    @Query("SELECT * FROM screenshots WHERE isDeleted = 0")
    suspend fun getAllScreenshots(): List<ScreenshotEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(screenshots: List<ScreenshotEntity>)

    @Query("UPDATE screenshots SET isDeleted = 1 WHERE id = :id")
    suspend fun softDelete(id: String)

    @Query("SELECT * FROM screenshots WHERE id = :id")
    suspend fun getScreenshotById(id: String): ScreenshotEntity?
}

@Dao
interface CollectionDao {
    @Query("SELECT * FROM collections")
    suspend fun getAllCollections(): List<CollectionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(collections: List<CollectionEntity>)

    @Query("SELECT * FROM collections WHERE id = :id")
    suspend fun getCollectionById(id: String): CollectionEntity?
}
