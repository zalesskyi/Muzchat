package com.zalesskyi.muzchat.features.chat.adapter.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zalesskyi.muzchat.features.chat.ChatContract

abstract class MessageViewHolder<T : ChatContract.MessageState>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(state: T)
}