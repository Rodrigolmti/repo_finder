package br.com.rodrigolmti.repo_finder.di

import br.com.rodrigolmti.injector.CoreComponent
import br.com.rodrigolmti.injector.coreComponent
import br.com.rodrigolmti.repo_finder.view.MainActivity
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun coreComponent(component: CoreComponent): Builder

        fun build(): AppComponent
    }

    companion object {

        fun inject(activity: MainActivity): AppComponent = DaggerAppComponent.builder()
            .coreComponent(activity.coreComponent())
            .build()
    }
}