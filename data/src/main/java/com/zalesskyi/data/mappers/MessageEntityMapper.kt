package com.zalesskyi.data.mappers

import com.zalesskyi.data.storage.models.MessageEntity
import com.zalesskyi.domain.models.Message

fun MessageEntity.toDomain() =
    Message(id, createdAt, body, senderId)

fun Message.toData() =
    MessageEntity(id, createdAt, body, senderId)