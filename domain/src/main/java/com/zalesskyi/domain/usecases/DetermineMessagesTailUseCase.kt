package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.extensions.diffInSeconds
import com.zalesskyi.domain.models.Displayable
import com.zalesskyi.domain.models.MessageModel
import com.zalesskyi.domain.usecases.base.UseCase
import javax.inject.Inject

const val TAIL_THRESHOLD_SECONDS = 20

/**
 * Determine the messages that should has a tail.
 *
 * Message has a tail if it satisfy the next conditions:
 * 1. The most recent message
 * 2. The message after it sent by another user
 * 3. The message after it was sent more than 20 seconds afterwards
 *
 * Input: list of messages
 * Output: IDs of messages, that is "tailed"
 */
class DetermineMessagesTailUseCase
@Inject
constructor() : UseCase<List<Displayable>, List<Long>>() {

    override fun execute(param: List<Displayable>): List<Long> {
        val messages = param.filterIsInstance<MessageModel>()
        return messages.mapIndexedNotNull { index, message ->
            message.id.takeIf {
                // For messages that satisfy this condition
                // we'll display the tails.
                index == messages.lastIndex
                        || message.senderId != messages[index + 1].senderId
                        || messages[index + 1].creationTime
                    .diffInSeconds(message.creationTime) >= TAIL_THRESHOLD_SECONDS
            }
        }
    }
}