package com.zalesskyi.muzchat.features.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.zalesskyi.domain.models.errors.InvalidMessageException
import com.zalesskyi.muzchat.R
import com.zalesskyi.muzchat.base.BaseFragment
import com.zalesskyi.muzchat.databinding.FragmentChatBinding
import com.zalesskyi.muzchat.extensions.clear
import com.zalesskyi.muzchat.extensions.hideKeyboard
import com.zalesskyi.muzchat.extensions.toast
import com.zalesskyi.muzchat.features.chat.adapter.ChatAdapter
import com.zalesskyi.muzchat.tools.viewBinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_chat.*

@AndroidEntryPoint
class ChatFragment : BaseFragment<ChatContract.State, ChatContract.Event, ChatContract.Effect, ChatViewModel>(R.layout.fragment_chat) {

    override val viewModel: ChatViewModel by viewModels<ChatViewModelImpl>()

    private val binding by viewBinding(FragmentChatBinding::bind)

    private val adapter = ChatAdapter(listOf())

    override fun observeState(state: ChatContract.State) {
        adapter.update(state.messages)
        rvChat.scrollToPosition(state.messages.lastIndex)
    }

    override fun observeEffect(effect: ChatContract.Effect?) {
        when (effect) {
            is ChatContract.Effect.Error -> showError(effect)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvChat.adapter = adapter
        binding.bSend.setOnClickListener {
            hideKeyboard()
            viewModel.sendEvent(
                ChatContract.Event.SendPressed(binding.etMessage.text.toString())
            )
            binding.etMessage.clear()
        }
    }

    private fun showError(error: ChatContract.Effect.Error) {
        when (error.exc) {
            is InvalidMessageException -> toast(R.string.message_invalid)
        }
    }
}