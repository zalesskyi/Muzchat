package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.extensions.diffInMinutes
import com.zalesskyi.domain.models.Displayable
import com.zalesskyi.domain.models.HeaderModel
import com.zalesskyi.domain.models.generateMessageId
import com.zalesskyi.domain.usecases.base.UseCase
import javax.inject.Inject

const val MINUTES_IN_HOUR = 60

/**
 * Insert timestamp headers following the next logic:
 *
 * "If the prev message was sent more than hour ago
 * or there is no previous messages"
 */
class InsertTimingHeadersUseCase
@Inject
constructor(): UseCase<List<Displayable>, List<Displayable>>() {

    override fun execute(param: List<Displayable>): List<Displayable> =
        param.toMutableList().also { states ->
            // get the messages with interval more than 1 hour, get their times
            val headersTimes = states.mapIndexedNotNull { index, message ->
                message.creationTime.takeIf {
                    index == 0 || states[index - 1].creationTime.diffInMinutes(message.creationTime) > MINUTES_IN_HOUR
                }
            }
            headersTimes.forEach { time ->
                // insert headers before the defined time positions
                states.add(HeaderModel(generateMessageId(), time.minus(1)))
            }
            // sort items by time
        }.sortedBy { it.creationTime }
}