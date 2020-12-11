package br.com.rodrigolmti.repository.domain.repository

import br.com.rodrigolmti.repository.domain.model.RepositoryModel

interface IRepoRepository {
    suspend fun getRepositoriesByQuery(
        query: String,
        page: Int,
    ): List<RepositoryModel>

    suspend fun getRepositoryById(id: Long): RepositoryModel?
}