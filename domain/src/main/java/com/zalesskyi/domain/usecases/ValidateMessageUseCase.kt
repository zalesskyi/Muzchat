package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.usecases.base.UseCase
import javax.inject.Inject

/**
 * Check if the message is valid so can be send to the chat.
 * I count the message valid if it is not blank.
 */
class ValidateMessageUseCase
@Inject
constructor() : UseCase<String, Boolean>() {

    /**
     * @return true if the message is valid, otherwise false.
     */
    override fun execute(param: String): Boolean = param.isNotBlank()
}