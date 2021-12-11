package com.zalesskyi.muzchat.features.chat

import com.zalesskyi.domain.models.Message
import com.zalesskyi.muzchat.base.UiEffect
import com.zalesskyi.muzchat.base.UiEvent
import com.zalesskyi.muzchat.base.UiState

class ChatContract {

    data class State(val messages: List<Message> = listOf(),
                     val isTyping: Boolean = false) : UiState

    sealed class Event : UiEvent {

        data class SendPressed(val messageBody: String) : Event()
    }

    sealed class Effect : UiEffect {

        data class Error(val exc: Exception) : Effect()
    }
}