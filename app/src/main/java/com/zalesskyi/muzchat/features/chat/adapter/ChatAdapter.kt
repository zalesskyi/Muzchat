package com.zalesskyi.muzchat.features.chat.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zalesskyi.muzchat.features.chat.ChatContract
import com.zalesskyi.muzchat.features.chat.adapter.holders.AnotherMessageViewHolder
import com.zalesskyi.muzchat.features.chat.adapter.holders.HeaderMessageViewHolder
import com.zalesskyi.muzchat.features.chat.adapter.holders.MessageViewHolder
import com.zalesskyi.muzchat.features.chat.adapter.holders.MyMessageViewHolder

class ChatAdapter(messages: List<ChatContract.MessageState>)
    : RecyclerView.Adapter<MessageViewHolder<ChatContract.MessageState>>() {

    companion object {

        private const val MY_MESSAGE_VIEW_TYPE = 0
        private const val ANOTHER_MESSAGE_VIEW_TYPE = 1
        private const val HEADER_MESSAGE_VIEW_TYPE = 2
    }

    private val items = messages.toMutableList()

    @Suppress("UNCHECKED_CAST")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder<ChatContract.MessageState> {
        return when (viewType) {
            MY_MESSAGE_VIEW_TYPE -> MyMessageViewHolder.newInstance(parent)
            ANOTHER_MESSAGE_VIEW_TYPE -> AnotherMessageViewHolder.newInstance(parent)
            else -> HeaderMessageViewHolder.newInstance(parent)
        } as MessageViewHolder<ChatContract.MessageState>
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ChatContract.MessageState.MyMessage -> MY_MESSAGE_VIEW_TYPE
            is ChatContract.MessageState.AnotherMessage -> ANOTHER_MESSAGE_VIEW_TYPE
            else -> HEADER_MESSAGE_VIEW_TYPE
        }
    }

    override fun onBindViewHolder(holder: MessageViewHolder<ChatContract.MessageState>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun update(newItems: List<ChatContract.MessageState>) {
        val diffResult = DiffUtil.calculateDiff(MessagesDiffCallback(items, newItems))
        items.clear()
        items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }
}