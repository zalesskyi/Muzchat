package com.zalesskyi.muzchat.features.chat

import com.zalesskyi.domain.models.errors.InvalidMessageException
import com.zalesskyi.domain.usecases.GenerateSmartReplyUseCase
import com.zalesskyi.domain.usecases.ObserveLastMessagesUseCase
import com.zalesskyi.domain.usecases.SendMessageUseCase
import com.zalesskyi.domain.usecases.ValidateMessageUseCase
import com.zalesskyi.muzchat.base.BaseViewModel
import com.zalesskyi.muzchat.base.BaseViewModelImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

interface ChatViewModel : BaseViewModel<ChatContract.State, ChatContract.Event, ChatContract.Effect>

@HiltViewModel
class ChatViewModelImpl
@Inject
constructor(private val observeLastMessagesUseCase: ObserveLastMessagesUseCase,
            private val validateMessageUseCase: ValidateMessageUseCase,
            private val generateSmartReplyUseCase: GenerateSmartReplyUseCase,
            private val sendMessageUseCase: SendMessageUseCase)
    : BaseViewModelImpl<ChatContract.State, ChatContract.Event, ChatContract.Effect>(),
    ChatViewModel {

    override val uiState = MutableStateFlow(initialState())

    override fun initialState() = ChatContract.State()

    override fun init() {
        super.init()
        observeLastMessages()
    }

    override fun onEventArrived(event: ChatContract.Event?) {
        when (event) {
            is ChatContract.Event.SendPressed -> sendMessage(event.messageBody)
        }
    }

    private fun observeLastMessages() {
        launch {
            observeLastMessagesUseCase.execute(Unit).collect {
                setState {
                    copy(messages = it)
                }
                generateSmartReplyUseCase.execute(it) {}
            }
        }
    }

    private fun sendMessage(body: String) {
        launch {
            if (validateMessageUseCase.execute(body)) {
                sendMessageUseCase.execute(params = body) {}
            } else {
                setEffect {
                    ChatContract.Effect.Error(InvalidMessageException())
                }
            }
        }
    }
}
