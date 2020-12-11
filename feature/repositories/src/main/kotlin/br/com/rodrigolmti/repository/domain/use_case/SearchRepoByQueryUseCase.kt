package br.com.rodrigolmti.repository.domain.use_case

import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.repository.IRepoRepository
import javax.inject.Inject

interface SearchRepoByQueryUseCase {
    suspend operator fun invoke(
        query: String,
        page: Int,
    ): List<RepositoryModel>
}

class SearchRepoByQuery @Inject constructor(
    private val repository: IRepoRepository
) : SearchRepoByQueryUseCase {

    override suspend fun invoke(
        query: String,
        page: Int,
    ): List<RepositoryModel> =
        repository.getRepositoriesByQuery(
            query = query,
            page = page,
        )
}