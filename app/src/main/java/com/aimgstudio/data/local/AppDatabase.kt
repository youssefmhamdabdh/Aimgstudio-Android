package com.aimgstudio.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aimgstudio.data.local.ScreenshotDao
import com.aimgstudio.data.local.CollectionDao
import com.aimgstudio.data.local.LogDao
import com.aimgstudio.data.local.ScreenshotEntity
import com.aimgstudio.data.local.CollectionEntity
import com.aimgstudio.data.models.LogEntry

@Database(entities = [ScreenshotEntity::class, CollectionEntity::class, LogEntry::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun screenshotDao(): ScreenshotDao
    abstract fun collectionDao(): CollectionDao
    abstract fun logDao(): LogDao
}
