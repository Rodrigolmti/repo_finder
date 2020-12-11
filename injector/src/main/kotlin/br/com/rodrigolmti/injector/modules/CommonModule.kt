package br.com.rodrigolmti.injector.modules

import br.com.rodrigolmti.kotlin_utils.ConnectionChecker
import br.com.rodrigolmti.kotlin_utils.IConnectionChecker
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
internal interface CommonModule {

    @[Binds Reusable]
    fun bindsIConnectionChecker(checker: ConnectionChecker): IConnectionChecker
}