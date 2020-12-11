package br.com.rodrigolmti.repository.di

import br.com.rodrigolmti.injector.CoreComponent
import br.com.rodrigolmti.injector.modules.ViewModelFactoryModule
import br.com.rodrigolmti.repository.di.modules.RepoSearchModule
import br.com.rodrigolmti.repository.view.detail.RepositoryDetailFragment
import br.com.rodrigolmti.repository.view.search.RepositorySearchFragment
import dagger.Component

@RepoSearchScope
@Component(
    modules = [ViewModelFactoryModule::class, RepoSearchModule::class],
    dependencies = [CoreComponent::class]
)
interface RepoSearchComponent {

    fun inject(fragment: RepositorySearchFragment)

    fun inject(fragment: RepositoryDetailFragment)

    @Component.Builder
    interface Builder {
        fun coreComponent(component: CoreComponent): Builder

        fun build(): RepoSearchComponent
    }
}