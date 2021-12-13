package com.zalesskyi.muzchat.features.chat.adapter.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zalesskyi.muzchat.R
import com.zalesskyi.muzchat.databinding.ItemHeaderMessageBinding
import com.zalesskyi.muzchat.features.chat.ChatContract
import com.zalesskyi.muzchat.tools.viewBinding.viewBinding

class HeaderMessageViewHolder(itemView: View) : MessageViewHolder<ChatContract.MessageState.HeaderMessage>(itemView) {

    companion object {

        fun newInstance(parent: ViewGroup) =
            HeaderMessageViewHolder(
                LayoutInflater.from(parent.context)
                .inflate(R.layout.item_header_message, parent, false))
    }

    private val binding by viewBinding(ItemHeaderMessageBinding::bind)

    override fun bind(state: ChatContract.MessageState.HeaderMessage) {
        binding.tvBody.text = state.time
    }
}