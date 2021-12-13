package com.zalesskyi.data.mappers

import com.zalesskyi.data.storage.models.MessageEntity
import com.zalesskyi.domain.models.MessageModel

fun MessageEntity.toDomain() =
    MessageModel(id, createdAt, body, senderId)

fun MessageModel.toData() =
    MessageEntity(id, createdAt, body, senderId)