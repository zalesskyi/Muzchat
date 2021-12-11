package com.zalesskyi.muzchat.base

import android.content.Context
import android.content.res.Configuration
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface BaseViewModel<STATE : UiState, EVENT : UiEvent, EFFECT : UiEffect> {

    val uiState: StateFlow<STATE>

    val eventFlow: SharedFlow<EVENT?>

    val effectFlow: SharedFlow<EFFECT?>

    fun init()

    fun sendEvent(event: EVENT?)

    fun reset()
}

abstract class BaseViewModelImpl<STATE : UiState, EVENT : UiEvent, EFFECT : UiEffect>
    : ViewModel(), BaseViewModel<STATE, EVENT, EFFECT> {

    override val eventFlow = MutableSharedFlow<EVENT?>()

    override val effectFlow = MutableSharedFlow<EFFECT?>()

    protected abstract fun initialState(): STATE

    protected abstract fun onEventArrived(event: EVENT?)

    @CallSuper
    override fun init() {
        viewModelScope.launch {
            eventFlow.collect(::onEventArrived)
        }
    }

    @CallSuper
    override fun sendEvent(event: EVENT?) {
        viewModelScope.launch {
            eventFlow.emit(event)
        }
    }

    @CallSuper
    override fun reset() {
        sendEvent(null)
    }

    protected fun launch(context: CoroutineContext = Dispatchers.Default, remoteCall: suspend () -> Unit) =
        viewModelScope.launch(context) {
            remoteCall()
        }

    protected fun setState(reducer: suspend STATE.() -> STATE) {
        launch {
            (uiState as? MutableStateFlow)?.value = uiState.value.let {
                it.reducer()
            }
        }
    }

    protected fun setEffect(builder: () -> EFFECT) {
        viewModelScope.launch {
            effectFlow.emit(builder())
        }
    }
}