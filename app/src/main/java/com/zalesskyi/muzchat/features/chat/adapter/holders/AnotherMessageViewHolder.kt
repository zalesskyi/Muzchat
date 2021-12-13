package com.zalesskyi.muzchat.features.chat.adapter.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zalesskyi.muzchat.R
import com.zalesskyi.muzchat.databinding.ItemAnotherMessageBinding
import com.zalesskyi.muzchat.features.chat.ChatContract
import com.zalesskyi.muzchat.features.chat.adapter.holders.MessageViewHolder
import com.zalesskyi.muzchat.tools.viewBinding.viewBinding

class AnotherMessageViewHolder(itemView: View) : MessageViewHolder<ChatContract.MessageState.AnotherMessage>(itemView) {

    companion object {

        fun newInstance(parent: ViewGroup) =
            AnotherMessageViewHolder(
                LayoutInflater.from(parent.context)
                .inflate(R.layout.item_another_message, parent, false))
    }

    private val binding by viewBinding(ItemAnotherMessageBinding::bind)

    override fun bind(state: ChatContract.MessageState.AnotherMessage) {
        binding.tvBody.run {
            text = state.message.body
            setBackgroundResource(getBackground(state.isTailed))
        }
    }

    private fun getBackground(isTailed: Boolean) =
        if (isTailed)
            R.drawable.bg_another_message_tail
        else
            R.drawable.bg_another_message
}