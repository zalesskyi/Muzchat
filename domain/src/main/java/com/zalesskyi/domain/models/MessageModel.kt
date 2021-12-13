package com.zalesskyi.domain.models

import org.joda.time.DateTime

data class MessageModel(

    /**
     * Unique id of message.
     */
    override val id: Long,

    /**
     * Timestamp of message creation.
     */
    val createdAt: Long,

    /**
     * The text body of message.
     */
    val body: String,

    /**
     * Id of sender.
     * @see CURRENT_USER_ID
     * @see ANOTHER_USER_ID
     */
    val senderId: Int
): Displayable {

    override val creationTime: DateTime
        get() = DateTime(createdAt)
}