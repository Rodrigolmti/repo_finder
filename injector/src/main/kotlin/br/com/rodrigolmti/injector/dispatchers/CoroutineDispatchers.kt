package br.com.rodrigolmti.injector.dispatchers

import br.com.rodrigolmti.kotlin_utils.ICoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class CoroutineDispatchers @Inject constructor() : ICoroutineDispatchers {
    override fun main(): CoroutineDispatcher = Dispatchers.Main

    override fun io(): CoroutineDispatcher = Dispatchers.IO
}