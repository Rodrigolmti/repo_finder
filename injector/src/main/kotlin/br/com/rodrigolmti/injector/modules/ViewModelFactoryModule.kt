package br.com.rodrigolmti.injector.modules

import androidx.lifecycle.ViewModelProvider
import br.com.rodrigolmti.injector.view_model.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @[Binds]
    abstract fun bindViewModelFactory(
        factory: ViewModelProviderFactory
    ): ViewModelProvider.Factory
}