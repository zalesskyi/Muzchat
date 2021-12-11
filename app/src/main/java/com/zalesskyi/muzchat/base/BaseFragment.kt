package com.zalesskyi.muzchat.base

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect

abstract class BaseFragment<STATE : UiState, EVENT : UiEvent, EFFECT : UiEffect, VM : BaseViewModel<STATE, EVENT, EFFECT>>(
    layoutRes: Int
) : Fragment(layoutRes) {

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.init()
        launchStateObserving()
        launchEffectObserving()
    }

    abstract fun observeState(state: STATE)

    abstract fun observeEffect(effect: EFFECT?)

    private fun launchStateObserving() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect(::observeState)
        }
    }

    private fun launchEffectObserving() {
        lifecycleScope.launchWhenStarted {
            viewModel.effectFlow.collect(::observeEffect)
        }
    }
}