package com.zalesskyi.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Messages")
data class MessageEntity(@PrimaryKey val id: Long,
                         val createdAt: Long,
                         val body: String,
                         val senderId: Int)