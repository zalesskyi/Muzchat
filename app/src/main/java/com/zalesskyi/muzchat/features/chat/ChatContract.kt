package com.zalesskyi.muzchat.features.chat

import com.zalesskyi.domain.models.MessageModel
import com.zalesskyi.muzchat.base.UiEffect
import com.zalesskyi.muzchat.base.UiEvent
import com.zalesskyi.muzchat.base.UiState

class ChatContract {

    /**
     * State of message.
     */
    sealed class MessageState {

        /**
         * Represents the message from current user.
         */
        data class MyMessage(val message: MessageModel, val isTailed: Boolean) : MessageState()

        /**
         * Represents the message from another user.
         */
        data class AnotherMessage(val message: MessageModel, val isTailed: Boolean) : MessageState()

        /**
         * Represents the header item with time of message.
         */
        data class HeaderMessage(val time: String) : MessageState()
    }

    /**
     * State of [ChatFragment] screen.
     */
    data class State(val messages: List<MessageState> = listOf(),
                     val isTyping: Boolean = false) : UiState

    /**
     * Set of events from [ChatFragment] screen.
     */
    sealed class Event : UiEvent {

        data class SendPressed(val messageBody: String) : Event()
    }

    /**
     * Set of effects of [ChatFragment] screen.
     */
    sealed class Effect : UiEffect {

        data class Error(val exc: Exception) : Effect()
    }
}