package com.aimgstudio.data.local

import androidx.room.*
import com.aimgstudio.data.models.LogEntry

@Dao
interface LogDao {
    @Query("SELECT * FROM logs ORDER BY timestamp DESC")
    suspend fun getAllLogs(): List<LogEntry>

    @Insert
    suspend fun insertLog(log: LogEntry)

    @Query("DELETE FROM logs")
    suspend fun clearLogs()
}
