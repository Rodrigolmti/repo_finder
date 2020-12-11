package br.com.rodrigolmti.kotlin_utils

import kotlinx.coroutines.CoroutineDispatcher

interface ICoroutineDispatchers {
    fun main(): CoroutineDispatcher
    fun io(): CoroutineDispatcher
}