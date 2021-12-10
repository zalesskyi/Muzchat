package com.zalesskyi.data.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MessageEntity(@PrimaryKey val id: Long)