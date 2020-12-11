package br.com.rodrigolmti.injector.modules

import br.com.rodrigolmti.injector.dispatchers.CoroutineDispatchers
import br.com.rodrigolmti.kotlin_utils.ICoroutineDispatchers
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
internal interface DispatcherModule {

    @[Binds Singleton]
    fun bindCoroutineDispatchers(
        coroutineDispatchersImpl: CoroutineDispatchers
    ): ICoroutineDispatchers
}