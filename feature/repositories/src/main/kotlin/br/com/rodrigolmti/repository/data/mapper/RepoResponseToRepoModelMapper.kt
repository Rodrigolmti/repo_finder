package br.com.rodrigolmti.repository.data.mapper

import br.com.rodrigolmti.kotlin_utils.Mapper
import br.com.rodrigolmti.repository.data.service.response.RepositoryResponse
import br.com.rodrigolmti.repository.domain.model.RepositoryModel
import br.com.rodrigolmti.repository.domain.model.RepositoryOwnerModel
import javax.inject.Inject

class RepoResponseToRepoModelMapper @Inject constructor() : Mapper<RepositoryResponse, RepositoryModel> {

    override fun invoke(from: RepositoryResponse) = RepositoryModel(
        id = from.id,
        name = from.name,
        private = from.private,
        owner = RepositoryOwnerModel(
            id = from.owner.id,
            avatarUrl = from.owner.avatarUrl,
            login = from.owner.login,
            url = from.owner.url,
            type = from.owner.type,
        ),
        description = from.description,
        url = from.url,
        createdAt = from.createdAt,
        lastUpdatedAt = from.lastUpdatedAt,
        stars = from.stars,
        watchersCount = from.watchersCount,
        score = from.score,
        defaultBranch = from.defaultBranch,
    )
}