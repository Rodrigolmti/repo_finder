package br.com.rodrigolmti.repository.di.modules

import androidx.lifecycle.ViewModel
import br.com.rodrigolmti.core_android.view_model.ViewModelKey
import br.com.rodrigolmti.repository.view.detail.RepositoryDetailViewModel
import br.com.rodrigolmti.repository.view.search.RepositoryListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RepoSearchPresentationModule {

    @[Binds IntoMap ViewModelKey(RepositoryListViewModel::class)]
    fun bindRepoListViewModel(
        viewModel: RepositoryListViewModel
    ): ViewModel

    @[Binds IntoMap ViewModelKey(RepositoryDetailViewModel::class)]
    fun bindRepoDetailViewModel(
        viewModel: RepositoryDetailViewModel
    ): ViewModel
}