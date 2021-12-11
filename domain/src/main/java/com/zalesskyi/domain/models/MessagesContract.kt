package com.zalesskyi.domain.models

import java.util.*

/**
 * Hardcoded ID of current user.
 */
const val CURRENT_USER_ID = 0

/**
 * Hardcoded ID of "another" user.
 */
const val ANOTHER_USER_ID = 1

/**
 * Generate "unique" ID for message.
 */
fun generateMessageId(): Long =
    UUID.randomUUID().mostSignificantBits and Long.MAX_VALUE