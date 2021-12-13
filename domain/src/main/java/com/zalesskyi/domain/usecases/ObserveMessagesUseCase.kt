package com.zalesskyi.domain.usecases

import com.zalesskyi.domain.models.Displayable
import com.zalesskyi.domain.models.MessageModel
import com.zalesskyi.domain.repositories.MessagesRepository
import com.zalesskyi.domain.usecases.base.UseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMessagesUseCase
@Inject
constructor(private val messagesRepository: MessagesRepository) : UseCase<Unit, Flow<List<Displayable>>>() {

    override fun execute(param: Unit): Flow<List<Displayable>> =
        messagesRepository.observeMessages()
}