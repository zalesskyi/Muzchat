package com.zalesskyi.domain.models

data class Message(

    /**
     * Unique id of message.
     */
    val id: Long,

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
)