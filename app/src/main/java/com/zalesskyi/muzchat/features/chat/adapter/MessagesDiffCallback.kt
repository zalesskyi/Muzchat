package com.zalesskyi.muzchat.features.chat.adapter

import androidx.recyclerview.widget.DiffUtil
import com.zalesskyi.muzchat.features.chat.ChatContract

class MessagesDiffCallback(oldItems: List<ChatContract.MessageState>,
                           newItems: List<ChatContract.MessageState>) : DiffUtil.Callback() {

    private val old = oldItems.toMutableList()
    private val new = newItems.toMutableList()

    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = old[oldItemPosition]
        val newItem = new[newItemPosition]
        return when {
            (oldItem is ChatContract.MessageState.MyMessage
                    && newItem is ChatContract.MessageState.MyMessage) ->
                oldItem.message.id == newItem.message.id
            (oldItem is ChatContract.MessageState.AnotherMessage
                    && newItem is ChatContract.MessageState.AnotherMessage) ->
                oldItem.message.id == newItem.message.id
            else -> oldItem === newItem
        }
    }

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        val oldItem = old[oldPosition]
        val newItem = new[newPosition]

        return when {
            (oldItem is ChatContract.MessageState.MyMessage
                    && newItem is ChatContract.MessageState.MyMessage) ->
                oldItem.message == newItem.message
            (oldItem is ChatContract.MessageState.AnotherMessage
                    && newItem is ChatContract.MessageState.AnotherMessage) ->
                oldItem.message == newItem.message
            else -> oldItem == newItem
        }
    }
}