package com.zalesskyi.muzchat.features.chat.adapter.holders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zalesskyi.muzchat.R
import com.zalesskyi.muzchat.databinding.ItemMyMessageBinding
import com.zalesskyi.muzchat.features.chat.ChatContract
import com.zalesskyi.muzchat.tools.viewBinding.viewBinding

class MyMessageViewHolder(itemView: View) :
    MessageViewHolder<ChatContract.MessageState.MyMessage>(itemView) {

    companion object {

        fun newInstance(parent: ViewGroup) =
            MyMessageViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_my_message, parent, false)
            )
    }

    private val binding by viewBinding(ItemMyMessageBinding::bind)

    override fun bind(state: ChatContract.MessageState.MyMessage) {
        binding.tvBody.run {
            text = state.message.body
            setBackgroundResource(getBackground(state.isTailed))
        }
    }

    private fun getBackground(isTailed: Boolean) =
        if (isTailed) R.drawable.bg_my_message_tail else R.drawable.bg_my_message
}