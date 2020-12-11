package br.com.rodrigolmti.repository.data.data_source

import br.com.rodrigolmti.repository.data.mapper.RepoResponseToRepoModelMapper
import br.com.rodrigolmti.repository.data.service.RepositorySearchApi
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import javax.inject.Inject

interface IRepoRemoteDataSource {

    suspend fun getRepositoriesByQuery(
        query: String,
        page: Int,
    ): List<RepositoryModel>

    suspend fun getRepositoryById(id: Long): RepositoryModel
}

class RepoRemoteDataSource @Inject constructor(
    private val responseMapper: RepoResponseToRepoModelMapper,
    private val repoSearchApi: RepositorySearchApi,
) : IRepoRemoteDataSource {

    override suspend fun getRepositoriesByQuery(
        query: String,
        page: Int,
    ): List<RepositoryModel> =
        repoSearchApi.searchRepoByQuery(
            query = query,
            page = page,
        ).items.map { responseMapper(it) }

    override suspend fun getRepositoryById(id: Long): RepositoryModel {
        val response = repoSearchApi.getRepoById(id = id)
        return responseMapper(response)
    }
}