package br.com.rodrigolmti.repository.data.repository

import br.com.rodrigolmti.kotlin_utils.IConnectionChecker
import br.com.rodrigolmti.kotlin_utils.ICoroutineDispatchers
import br.com.rodrigolmti.repository.data.data_source.IRepoLocalDataSource
import br.com.rodrigolmti.repository.data.data_source.IRepoRemoteDataSource
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.repository.IRepoRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val remoteDataSource: IRepoRemoteDataSource,
    private val localDataSource: IRepoLocalDataSource,
    private val connectionChecker: IConnectionChecker,
    private val dispatchers: ICoroutineDispatchers,
) : IRepoRepository {

    override suspend fun getRepositoriesByQuery(
        query: String,
        page: Int,
    ): List<RepositoryModel> {
        return withContext(dispatchers.io()) {
            if (connectionChecker.isDeviceConnected()) {
                val repositories = remoteDataSource.getRepositoriesByQuery(
                    query = query,
                    page = page,
                )
                if (repositories.isNotEmpty()) {
                    localDataSource.saveRepositories(repositories = repositories)
                }
                repositories
            } else {
                localDataSource.getRepositoriesByQuery(query = query)
            }
        }
    }

    override suspend fun getRepositoryById(id: Long): RepositoryModel? {
        return withContext(dispatchers.io()) {
            if (connectionChecker.isDeviceConnected()) {
                remoteDataSource.getRepositoryById(id = id)
            } else {
                localDataSource.getRepositoryById(id = id)
            }
        }
    }
}