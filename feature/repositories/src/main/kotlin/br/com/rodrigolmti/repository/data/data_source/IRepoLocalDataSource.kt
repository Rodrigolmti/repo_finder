package br.com.rodrigolmti.repository.data.data_source

import br.com.rodrigolmti.repository.data.database.dao.RepositoryDao
import br.com.rodrigolmti.repository.data.mapper.RepoEntityToRepoModelMapper
import br.com.rodrigolmti.repository.data.mapper.RepoModelToRepoEntityMapper
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import javax.inject.Inject

interface IRepoLocalDataSource {
    suspend fun saveRepositories(repositories: List<RepositoryModel>)

    suspend fun getRepositoryById(id: Long): RepositoryModel?

    suspend fun getRepositoriesByQuery(query: String): List<RepositoryModel>
}

class RepoLocalDataSource @Inject constructor(
    private val entityMapper: RepoEntityToRepoModelMapper,
    private val modelMapper: RepoModelToRepoEntityMapper,
    private val repoDao: RepositoryDao,
) : IRepoLocalDataSource {

    override suspend fun saveRepositories(repositories: List<RepositoryModel>) {
        val entities = repositories.map { modelMapper(it) }
        repoDao.saveRepositories(entities)
    }

    override suspend fun getRepositoryById(id: Long): RepositoryModel? =
        repoDao.getRepositoryById(id = id)?.let(entityMapper)

    override suspend fun getRepositoriesByQuery(query: String): List<RepositoryModel> =
        repoDao.getRepositoriesByQuery(query = query)?.map {
            entityMapper(it)
        } ?: emptyList()
}