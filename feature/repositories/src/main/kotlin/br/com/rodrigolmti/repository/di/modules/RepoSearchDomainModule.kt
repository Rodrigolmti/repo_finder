package br.com.rodrigolmti.repository.di.modules

import br.com.rodrigolmti.repository.data.repository.RepoRepository
import br.com.rodrigolmti.repository.domain.repository.IRepoRepository
import br.com.rodrigolmti.repository.domain.use_case.GetRepoById
import br.com.rodrigolmti.repository.domain.use_case.GetRepoByIdUseCase
import br.com.rodrigolmti.repository.domain.use_case.SearchRepoByQuery
import br.com.rodrigolmti.repository.domain.use_case.SearchRepoByQueryUseCase
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface RepoSearchDomainModule {

    @[Binds Reusable]
    fun bindsRepoSearchRepository(repository: RepoRepository): IRepoRepository

    @[Binds Reusable]
    fun bindsSearchRepoByQueryUseCase(useCase: SearchRepoByQuery): SearchRepoByQueryUseCase

    @[Binds Reusable]
    fun bindsGetRepoByIdUseCase(useCase: GetRepoById): GetRepoByIdUseCase
}