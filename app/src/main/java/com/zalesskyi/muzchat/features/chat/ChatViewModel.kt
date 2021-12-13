package com.zalesskyi.muzchat.features.chat

import com.zalesskyi.domain.models.*
import com.zalesskyi.domain.models.errors.InvalidMessageException
import com.zalesskyi.domain.usecases.*
import com.zalesskyi.muzchat.base.BaseViewModel
import com.zalesskyi.muzchat.base.BaseViewModelImpl
import com.zalesskyi.muzchat.tools.TIME_PATTERN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

interface ChatViewModel : BaseViewModel<ChatContract.State, ChatContract.Event, ChatContract.Effect>

@HiltViewModel
class ChatViewModelImpl
@Inject
constructor(private val observeMessagesUseCase: ObserveMessagesUseCase,
            private val validateMessageUseCase: ValidateMessageUseCase,
            private val determineMessagesTailUseCase: DetermineMessagesTailUseCase,
            private val insertTimingHeadersUseCase: InsertTimingHeadersUseCase,
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
            observeMessagesUseCase.execute(Unit).collect {
                val tailedMessagesIds = determineMessagesTailUseCase.execute(it)
                setState {
                    copy(messages = insertTimingHeadersUseCase
                        .execute(it)
                        .toStates(tailedMessagesIds))
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

    private fun List<Displayable>.toStates(tailedMessagesIds: List<Long>): List<ChatContract.MessageState> =
        mapNotNull {
            val isTailed = tailedMessagesIds.contains(it.id)
            when {
                it is HeaderModel -> {
                    ChatContract.MessageState.HeaderMessage(
                        it.creationTime.toString(TIME_PATTERN)
                    )
                }
                it is MessageModel && it.senderId == CURRENT_USER_ID -> {
                    ChatContract.MessageState.MyMessage(it, isTailed)
                }
                it is MessageModel && it.senderId == ANOTHER_USER_ID -> {
                    ChatContract.MessageState.AnotherMessage(it, isTailed)
                }
                else -> null
            }
        }
}
