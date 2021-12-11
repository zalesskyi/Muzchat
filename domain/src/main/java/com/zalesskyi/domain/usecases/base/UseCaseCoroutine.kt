package com.zalesskyi.domain.usecases.base

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

typealias CompletionBlock<T> = UseCaseCoroutine.Request<T>.() -> Unit

abstract class UseCaseCoroutine<in Params, T>() {

    protected abstract suspend fun executeOnBackground(params: Params): T

    suspend fun execute(params: Params, block: CompletionBlock<T>) {

        val response = Request<T>().apply(block).also { it.onStart?.invoke() }

        try {
            val result = withContext(Dispatchers.IO) {
                executeOnBackground(params)
            }
            response.onComplete(result)
        } catch (cancellationException: CancellationException) {
            response.onCancel?.invoke(cancellationException)
        } catch (e: Throwable) {
            response.onError?.invoke(e)
        } finally {
            response.onTerminate?.invoke()
        }
    }

    class Request<T> {
        lateinit var onComplete: (T) -> Unit
        var onError: ((Throwable) -> Unit)? = null
        var onCancel: ((CancellationException) -> Unit)? = null
        var onStart: (() -> Unit)? = null
        var onTerminate: (() -> Unit)? = null
    }
}