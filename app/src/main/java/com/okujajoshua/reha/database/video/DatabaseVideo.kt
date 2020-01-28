package com.okujajoshua.reha.database.video

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.okujajoshua.reha.domain.DevByteVideo

@Entity(tableName = "videos_table")
data class DatabaseVideo constructor(
    @PrimaryKey
    val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String

)

fun List<DatabaseVideo>.asDomainModel(): List<DevByteVideo> {
    return map {
        DevByteVideo(
            url = it.url,
            title = it.title,
            description = it.description,
            updated = it.updated,
            thumbnail = it.thumbnail
        )
    }
}


