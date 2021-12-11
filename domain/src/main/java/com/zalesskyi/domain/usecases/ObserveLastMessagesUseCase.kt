package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.models.Message
import com.zalesskyi.domain.repositories.MessagesRepository
import com.zalesskyi.domain.usecases.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveLastMessagesUseCase
@Inject
constructor(private val messagesRepository: MessagesRepository) : UseCase<Unit, Flow<List<Message>>>() {

    override fun execute(param: Unit): Flow<List<Message>> =
        messagesRepository.observeMessages()
}