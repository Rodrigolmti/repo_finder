package br.com.rodrigolmti.repository.domain.use_case

import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.repository.IRepoRepository
import javax.inject.Inject

interface GetRepoByIdUseCase {
    suspend operator fun invoke(id: Long): RepositoryModel?
}

class GetRepoById @Inject constructor(
    private val repository: IRepoRepository
) : GetRepoByIdUseCase {

    override suspend fun invoke(id: Long): RepositoryModel? = repository.getRepositoryById(id = id)
}